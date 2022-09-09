/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Class constructor for the WolfScheduler program. Creates a course objects
 * which is used for scheduling, adding, and dropping courses on WolfScheduler.
 * Contains the following fields: meeting days, start time, end time, credit
 * hours, course title, course name, course section and instructor ID. Contains
 * the following methods: getters and setters for each field.
 * 
 * 
 * @author Jay Shah (jsshah)
 *
 */
public class Course {

	/** Minimum length allowed for Course's name */
	private static final int MIN_NAME_LENGTH = 5;
	/** Maximum length allowed for Course's name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Minimum length allowed for Course's character count */
	private static final int MIN_LETTER_COUNT = 1;
	/** Maximum length allowed for Course's character count */
	private static final int MAX_LETTER_COUNT = 4;
	/**
	 * Max amount of digits allowed for entry by user when searching a Course's
	 * number identity
	 */
	private static final int DIGIT_COUNT = 3;
	/** Max amount of digits allowed for the section length. */
	private static final int SECTION_LENGTH = 3;
	/** Minimum amount of credits Course is allowed to have */
	private static final int MIN_CREDITS = 3;
	/** Maximum amount of credits Course is allowed to have */
	private static final int MAX_CREDITS = 5;
	/** Maximum amount of hours according to military time */
	private static final int UPPER_HOUR = 24;
	/** Maximum amount of minutes in an hour */
	private static final int UPPER_MINUTE = 60;
	/** Number to divide military time by for minutes */
	static final int MILITARY_TIME_DIVIDE = 100;
	/** Minimum number that determines if military time is PM or AM */
	static final int MILITARY_PM_TIME = 12;
	/**
	 * Categorizes minute to see if additional formatting is required for the ones
	 * place
	 */
	static final int IS_SINGLE_DIGIT_DIVIDER = 10;

	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructs a Course object with values for all the fields.
	 * 
	 * @param name         name of the Course
	 * @param title        title of the Course
	 * @param section      section of the Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {

		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);

	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		int letterAmount = 0;
		int digitAmount = 0;
		boolean isSpace = false;

		for (int i = 0; i < name.length(); i++) {
			if (!isSpace) {
				if (Character.isLetter(name.charAt(i))) {
					letterAmount++;
				} else if (" ".equals(String.valueOf(name.charAt(i)))) {
					isSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (isSpace) {
				if (Character.isDigit(name.charAt(i))) {
					digitAmount++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		if (letterAmount < MIN_LETTER_COUNT || letterAmount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (digitAmount != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the parameter is null or empty
	 */
	public void setTitle(String title) {

		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Returns the Course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the param is null, empty, or contains a
	 *                                  character
	 */
	public void setSection(String section) {

		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}

	/**
	 * Return's the Course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are greater than max or less than
	 *                                  minimum
	 */
	public void setCredits(int credits) {

		if (credits > MAX_CREDITS || credits < MIN_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Return's the Course's instructor ID.
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if param is null or empty
	 */
	public void setInstructorId(String instructorId) {

		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Returns the Course's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns the Course's meeting days, start time, and end time
	 * 
	 * @param meetingDays days the Course will meet for class
	 * @param startTime   time the Course will start
	 * @param endTime     time the Course will end
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Check to ensure input is not null or empty string
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		// Check to ensure arranged courses do not have start and end times
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		else {
			// call to helper method to make sure meeting days are appropriately entered
			if (meetingDaysContainsInvalidCharacters(meetingDays)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;

		}

		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// calls helper method militaryTimeToHours and Minutes to make code easier to
		// follow
		int startHour = militaryTimeToHours(startTime);
		int startMinute = militaryTimeToMinutes(startTime);

		// calls helper method militaryTimeToHours and Minutes to make code easier to
		// follow
		int endHour = militaryTimeToHours(endTime);
		int endMinute = militaryTimeToMinutes(endTime);

		// checks to ensure start time is valid and within the upper and lower limits
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks to ensure start time is valid and within the upper and lower limits
		if (startMinute < 0 || startMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks to ensure end time is valid and within the upper and lower limits
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// checks to ensure end time is valid and within the upper and lower limits
		if (endMinute < 0 || endMinute >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * takes the time passed through in military and uses final variable to convert
	 * to standard time
	 * 
	 * @param time time in military time
	 * @return time hour in military time
	 */
	public int militaryTimeToHours(int time) {

		return time / MILITARY_TIME_DIVIDE;

	}

	/**
	 * takes the time passed through in military and uses final variable to convert
	 * to standard time
	 * 
	 * @param time time in military time
	 * @return time minutes in military time
	 */
	public int militaryTimeToMinutes(int time) {
		return time % MILITARY_TIME_DIVIDE;
	}

	/**
	 * Helper method to make sure meetingDays does not have invalid characters. Uses
	 * counters and compares the values to a total to ensure validity.
	 * 
	 * @param meetingDays is the meetingDays which need to be checked
	 * @return true if meetingDays has invalid characters and false if no invalid
	 *         characters are found.
	 */
	public boolean meetingDaysContainsInvalidCharacters(String meetingDays) {

		boolean containsInvalidChar = false;
		
		//initialize counters for course days
		int mCount = 0;
		int tCount = 0;
		int wCount = 0;
		int thCount = 0;
		int fCount = 0;
		
		//iterate through the meeting days input
		for (int i = 0; i < meetingDays.length(); i++) {

			char currentCharacter = meetingDays.charAt(i);
			
			//compare iteration of string to each character possible and increase count
			if (currentCharacter == 'M') {
				mCount++;
			} else if (currentCharacter == 'T') {
				tCount++;
			} else if (currentCharacter == 'W') {
				wCount++;
			} else if (currentCharacter == 'H') {
				thCount++;
			} else if (currentCharacter == 'F') {
				fCount++;
			} else {
				containsInvalidChar = true;
			}
		}
		
		//easy if statement to check if invalid characters are present
		if (mCount > 1 || tCount > 1 || wCount > 1 || thCount > 1
				|| fCount > 1) {
			containsInvalidChar = true;
		}
		//return boolean value back to setMeetingDaysAndTime method
		return containsInvalidChar;
	}

	/**
	 * Converts military time to standard time and gives a meridian string value
	 * 
	 * 
	 * @return meetingDaysString standard time of Course start and end with meridian
	 *         value
	 */
	public String getMeetingString() {
		
		//If meeting day is arranged than method returns arranged and stops
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		}
		
		//initialize the start time and end time to "any" meridian value
		//"any" in this case means that it does not matter if it is am or pm
		//because it will be potentially changed in the below if statements
		//for this case we are using "AM" but it could also be "PM"
		String startTimeDayValue = "AM";
		String endTimeDayValue = "AM";
		
		//call helper method to convert start time to standard time
		int startHour = militaryTimeToHours(this.startTime);
		int startMinute = militaryTimeToMinutes(this.startTime);
		
		//call helper method to convert end time to standard time
		int endHour = militaryTimeToHours(this.endTime);
		int endMinute = militaryTimeToMinutes(this.endTime);

		//create new variable where we can store standard time as a string
		String startHourString;
		String endHourString;

		//create a new variable where we can store standard time as a string
		String startMinuteString;
		String endMinuteString;

		//compares start time hour to 12 to see if it should have a PM meridian value
		if (startHour >= MILITARY_PM_TIME) {
			startTimeDayValue = "PM";
		}
		
		//compares end time hour to 12 to see if it should have a PM meridian value
		if (endHour >= MILITARY_PM_TIME) {
			endTimeDayValue = "PM";
		}
		
		//compares start time hour to 12 and if it is greater than 12, but not equal, 
		//subtract 12 and add PM meridian value
		if (startHour > MILITARY_PM_TIME) {

			startHour -= MILITARY_PM_TIME;

			startTimeDayValue = "PM";

		//if start time hour is 0 then time is midnight
		} else if (startHour == 0) {
			startHour = MILITARY_PM_TIME;

			startTimeDayValue = "PM";

		}

		//if end time hour is greater than 12 but not equal then subtract 12
		//and add PM meridian tag
		if (endHour > MILITARY_PM_TIME) {

			endHour -= MILITARY_PM_TIME;

			endTimeDayValue = "PM";

		//if start time hour is 0 then it is midnight
		} else if (startHour == 0) {
			startHour = MILITARY_PM_TIME;

			endTimeDayValue = "PM";

		}

		//convert int to string
		startHourString = Integer.toString(startHour);
		endHourString = Integer.toString(endHour);

		//convert int to string
		startMinuteString = Integer.toString(startMinute);
		endMinuteString = Integer.toString(endMinute);

		//if start time minute has a ones place not divisible by 0 (no number
		//in the ones place is cleanly divisible by 0)
		//then concatenate a 0 and add the ones place number to get a leading zero
		//value
		if (startMinute < IS_SINGLE_DIGIT_DIVIDER) {
			startMinuteString = "0" + startMinute;
		}
		
		//same thing as above, except for now we are working with the end time
		//minute
		if (endMinute < IS_SINGLE_DIGIT_DIVIDER) {
			endMinuteString = "0" + endMinute;
		}
		
		//this block of code concatenates the all the times above to create one final
		//meetingDaysString in the format "00:00 - 00:00"
		String meetingDaysString = "";
		meetingDaysString += this.meetingDays;
		meetingDaysString += " ";
		meetingDaysString += startHourString;
		meetingDaysString += ":";
		meetingDaysString += startMinuteString;
		meetingDaysString += startTimeDayValue;
		meetingDaysString += "-";
		meetingDaysString += endHourString;
		meetingDaysString += ":";
		meetingDaysString += endMinuteString;
		meetingDaysString += endTimeDayValue;

		return meetingDaysString;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		return Objects.hash(credits, endTime, instructorId, meetingDays, name, section, startTime, title);
	}

	/**
	 * Compares a give object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && endTime == other.endTime && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(meetingDays, other.meetingDays) && Objects.equals(name, other.name)
				&& Objects.equals(section, other.section) && startTime == other.startTime
				&& Objects.equals(title, other.title);
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(meetingDays)) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}

	/**
	 * Main left unpopulated for now.
	 * 
	 * @param args command line arguments not used
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
