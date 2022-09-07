/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import java.util.Objects;

/**
 * Class constructor for the WolfScheduler program
 * 
 * @author jayshah
 *
 */
public class Course {

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
	 * Minimum length allowed for Course's name
	 */
	private static final int MIN_NAME_LENGTH = 5;
	/**
	 * Maximum length allowed for Course's name
	 */
	private static final int MAX_NAME_LENGTH = 8;
	/**
	 * Minimum length allowed for Course's character count
	 */
	private static final int MIN_LETTER_COUNT = 1;
	/**
	 * Maximum length allowed for Course's character count
	 */
	private static final int MAX_LETTER_COUNT = 4;
	/**
	 * Max amount of digits allowed for entry by user when searching a Course's
	 * number identity
	 */
	private static final int DIGIT_COUNT = 3;
	/**
	 * Max amount of digits allowed for the section length.
	 */
	private static final int SECTION_LENGTH = 3;
	/**
	 * Minimum amount of credits Course is allowed to have
	 */
	private static final int MIN_CREDITS = 3;
	/**
	 * Maximum amount of credits Course is allowed to have
	 */
	private static final int MAX_CREDITS = 5;
	/**
	 * Maximum amount of hours according to military time
	 */
	private static final int UPPER_HOUR = 24;
	/**
	 * Maximum amount of minutes in an hour
	 */
	private static final int UPPER_MINUTE = 60;

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
		this.name = name;
		this.title = title;
		this.section = section;
		this.credits = credits;
		this.instructorId = instructorId;
		this.meetingDays = meetingDays; // How can I update the constructor to use the method setMeetingDaysAndTime
		this.startTime = startTime;
		this.endTime = endTime;
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

		int letter_counter = 0;
		int digit_counter = 0;
		boolean space = false;

		for (int i = 0; i < name.length(); i++) {
			if (Character.isWhitespace(name.charAt(i))) {
				if (Character.isLetter(name.charAt(i))) {
					letter_counter++;
				} else if (Character.isWhitespace(name.charAt(i))) {
					space = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (Character.isWhitespace(name.charAt(i))) {
				if (Character.isDigit(name.charAt(i))) {
					digit_counter++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}
		if (letter_counter < 1 || letter_counter > 4) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (digit_counter != 3) {
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
	 */
	public void setTitle(String title) {
		this.title = title;

		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
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
	 */
	public void setSection(String section) {
		this.section = section;

		if (section == null || section.length() != 3) {
			throw new IllegalArgumentException("Invalid section.");
		}

		for (int i = 0; i < section.length(); i++) {
			if (Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
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
	 */
	public void setCredits(int credits) {
		this.credits = credits;

		if (credits > MAX_CREDITS || credits < MIN_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
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
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;

		if (instructorId == null) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		for (int i = 0; i < instructorId.length(); i++) {
			if (Character.isWhitespace(instructorId.charAt(i))) {
				throw new IllegalArgumentException("Invalid instructor id.");
			}
		}
	}

	/**
	 * Returns the Course's meetind days.
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
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (!("M".equals(meetingDays) && "T".equals(meetingDays) && "W".equals(meetingDays) && "H".equals(meetingDays)
				&& "F".equals(meetingDays) && "A".equals(meetingDays))) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			int aCount = 0;

			for (int i = 0; i < meetingDays.length(); i++) {
				if ("M".equals(meetingDays)) {
					mCount++;
				}
				if ("T".equals(meetingDays)) {
					tCount++;
				}
				if ("W".equals(meetingDays)) {
					wCount++;
				}
				if ("H".equals(meetingDays)) {
					hCount++;
				}
				if ("F".equals(meetingDays)) {
					fCount++;
				}
				if ("A".equals(meetingDays)) {
					aCount++;
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			if (mCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (tCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (wCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (hCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			if (aCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;

			if (startHour < 0 || startHour > 23) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (startMin < 0 || startMin > 59) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endHour < 0 || endHour > 23) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			if (endMin < 0 || endMin > 59) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
	}

	/**
	 * Converts military time to standard time
	 * 
	 * @param startTime start time of Course
	 * @param endTime   end time of Course
	 * @return standard time of Course start and end
	 */
	public String getMeetingString(int startTime, int endTime) {

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		String meridian = "AM";

		if (startHour > 12) {
			startHour = startHour - 12;
			meridian = "PM";
		}

		if (startHour == 0) {
			meridian = "AM";
		}

		if (endHour > 12) {
			endHour = endHour - 12;
			meridian = "PM";
		}

		if (endHour == 0) {
			meridian = "AM";
		}
		// How should the new time format be returned?
		return startHour + ":" + startMin + " " + meridian;

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
	 * @param args args command line tools not used
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
