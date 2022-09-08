/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Class used for scheduling courses, adding events to schedule, getting courses from catalog, exporting schedules,
 * removing courses from schedule, setting schedule title, getting schedule title, 
 * presenting activities, and adding events.
 * 
 * @author jayshah
 *
 */
public class WolfScheduler {
	
	/** Number of columns in the 2D array for Course objects */
	static final int COLUMNS_FOR_ACTIVITY_OBJECTS = 4;
	/** Number of columns for a 2D array for a full schedule of activities */
	static final int COLUMNS_FOR_FULL_ACTIVITY_OBJECTS = 7;
	/** Index for Course's 2D array with Course's name */
	static final int INDEX_FOR_COURSE_NAME = 0;
	/** Index for Course's 2D array with the meeting days */
	static final int INDEX_FOR_COURSE_MEETINGDAYS = 5;
	/** Index for Course's 2D array containing the instructorId */
	static final int INDEX_FOR_COURSE_INSTRUCTORID = 4;
	/** Index for Course's 2D array containing the credits */
	static final int INDEX_FOR_COURSE_CREDIT = 3;
	/** Index for Course's 2D array containing the title */
	static final int INDEX_FOR_COURSE_TITLE = 2;
	/** Index for a Course's 2D array containing it's section */
	static final int INDEX_FOR_COURSE_SECTION = 1;
	
	/** courses available for use with scheduler and registration */
	private ArrayList<Course> courseCatalog;
	
	/** courses actively added to a user's schedule */
	ArrayList<Course> schedule;
	
	/** title for schedule */
	String title;
	
	
	/** Schedule name */
	public static final String DEFAULT_SCHEDULE_NAME = "My Schedule";
	/**
	 * Creates WolfSchedule with a user's given file name and contain course objects.
	 * 
	 * @param validTestFile file with Course objects
	 * @throws IllegalArgumentException when file is not present or found
	 */
	public WolfScheduler(String validTestFile) {
		
		//Create course catalog
		ArrayList<Course> c = new ArrayList<Course>();
		this.courseCatalog = c;
		
		//creating schedule
		ArrayList<Course> s = new ArrayList<Course>();
		this.schedule = s;
		
		this.title = DEFAULT_SCHEDULE_NAME;
		
		try {
			this.courseCatalog = CourseRecordIO.readCourseRecords(validTestFile);
		} catch (FileNotFoundException e){
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * main method not used
	 * 
	 * @param args command line arguments not used
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * Creates and returns 2D array of the schedule containing info about eh course name, section, and title
	 * 
	 * @return 2D string array of the schedule
	 */
	public String[][] getScheduledCourses() {
		
		int numRow = this.schedule.size();
		
		if (numRow == 0) {
			return new String[0][0];
		}
		
		int numCol = 3;
		
		String[][] schedStr = new String[numRow][numCol];
		
		for (int i = 0; i < numRow; i++) {
			
			schedStr[i][0] = this.schedule.get(i).getName();
			
			schedStr[i][1] = this.schedule.get(i).getSection();
			
			schedStr[i][2] = this.schedule.get(i).getTitle();
		}
		return schedStr;
	}
	
	/**
	 * Returns the wolf scheduler catalog in a 2D String array
	 * 
	 * @return catalog catalog of courses
	 */
	public String[][] getCourseCatalog() {
		
		//number of rows is set by the number of courses in the catalog
		int numRow = this.courseCatalog.size();
		
		//no courses in the catalog means empty array
		if (numRow == 0) {
			return new String [0][0];
		}
		
		//Three columns with a name, title, and section
		int numCol = 3;
		
		//creating a string array with one row per course in the catalog
		String[][] catStr = new String[numRow][numCol];
		
		//Index through the catalog, find the relevant and pertinent data for each course
		//then add the data to the 2D array
		for (int i = 0; i < numRow; i++) {
			//adding course name
			catStr[i][0] = this.courseCatalog.get(i).getName();
			//adding the course section to the array
			catStr[i][1] = this.courseCatalog.get(i).getSection();
			//adding the course title to array
			catStr[i][2] = this.courseCatalog.get(i).getTitle();
			//for loop will continue to add data for the next courses in the catalog
		}
		
		return catStr;
	}
	
	/**
	 * Creates and returns a 2D array of the schedule containing info about the course's name, section, 
	 * title, credits, instructorId and meetingDays
	 * 
	 * @return 2D array of the schedule
	 */
	public String[][] getFullScheduledCourses() {
		
		int numRow = this.schedule.size();
		
		if (numRow == 0) {
			return new String[0][0];
		}
		
		int numCol = 6;
		
		String[][] fullSchedStr = new String[numRow][numCol];
		
		for (int i = 0; i < numRow; i++) {
			
			fullSchedStr[i][0] = this.schedule.get(i).getName();
			
			fullSchedStr[i][1] = this.schedule.get(i).getSection();
			
			fullSchedStr[i][2] = this.schedule.get(i).getTitle();
			
			fullSchedStr[i][3] = "" + this.schedule.get(i).getCredits();
			
			fullSchedStr[i][4] = this.schedule.get(i).getInstructorId();
			
			fullSchedStr[i][5] = this.schedule.get(i).getMeetingString();
		}
		return fullSchedStr;
	}
	
	/**
	 * Gets schedule title
	 * @return schedule title
	 */
	public String getScheduleTitle() {
		
		return this.title;
	}
	
	/**
	 * Exports schedule to a specified file
	 * 
	 * @param fileName the name of the file the user will export to
	 * @throws IllegalArgumentException if file cannot be written to
	 */
	public void exportSchedule(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, this.schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
	
	/**
	 * Retrieves course from the course catalog based on input name and section number
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return the course object corresponding to name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.courseCatalog.size(); i++) {
			
			if (this.courseCatalog.get(i).getName().equals(name) && 
					this.courseCatalog.get(i).getSection().equals(section)) {
				
				return this.courseCatalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Checks if course can be added to schedule and adds if it passes
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return true or false depending on if course exists or not already
	 */
	public boolean addCourseToSchedule(String name, String section) {
		
		//check if course exists in catalog
		if (this.getCourseFromCatalog(name, section) == null) {
			return false;
		}
		
		//check for course with same name in catalog
		for (int i = 0; i < this.schedule.size(); i++) {
			if (this.schedule.get(i).getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		
		this.schedule.add(this.schedule.size(), this.getCourseFromCatalog(name, section));
		return true;
		
	}
	
	/**
	 * Checks if course can be removed from schedule then removes it
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return true or false depending on if course exists or does not exist in schedule
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		
		//check if the current indexed course is in the schedule has the same name 
		//and section as the input name and section
		for (int i = 0; i < this.schedule.size(); i++) {
			if (this.schedule.get(i).getName().equals(name) && 
					//if found then remove the course and return true
					this.schedule.get(i).getSection().equals(section)) {
				this.schedule.remove(this.getCourseFromCatalog(name, section));
				return true;
			}
		}
		
		return false;
		
	}

	/**
	 * Creates new empty schedule
	 */
	public void resetSchedule() {
		//create new array that is empty
		ArrayList<Course> newEmptySchedule = new ArrayList<Course>();
		//set the schedule field to the new empty schedule objects
		this.schedule = newEmptySchedule;
		
	}
	
	/**
	 * sets title for schedule
	 * 
	 * @param title title of schedule
	 * @throws IllegalArgumentException if input is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		} else {
			this.title = title;
		}
		
	}

}
