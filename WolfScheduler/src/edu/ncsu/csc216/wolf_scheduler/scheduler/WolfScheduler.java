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
 * @author Jay Shah (jsshah)
 *
 */
public class WolfScheduler {
	
	/** courses available for use with scheduler and registration */
	private ArrayList<Course> courseCatalog;
	
	/** courses actively added to a user's schedule */
	ArrayList<Course> schedule;
	
	/** title for schedule */
	String title;
	
	/** Schedule name */
	public static final String DEFAULT_SCHEDULE_NAME = "My Schedule";
	
	/**
	 * Creates WolfSchedule with a user's given file name and contains course objects.
	 * Attempts to populate Course catalog with courses that are read from an input file.
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
		
		//set title to My Schedule as a deafault
		this.title = DEFAULT_SCHEDULE_NAME;
		
		//set up a try catch block in case file is not found
		try {
			this.courseCatalog = CourseRecordIO.readCourseRecords(validTestFile);
		} catch (FileNotFoundException e){
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Main method not used
	 * 
	 * @param args command line arguments not used
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Creates and returns 2D array of the schedule containing info about 
	 * the course name, section, and title.
	 * 
	 * @return 2D string array of the schedule
	 */
	public String[][] getScheduledCourses() {
		
		//number of rows will be set by amount of courses
		int numRow = this.schedule.size();
		
		//if the amount of rows is 0 then there are no courses in catalog
		//ensures that catalog is empty
		if (numRow == 0) {
			return new String[0][0];
		}
		
		//three columns for name, section, and title
		int numCol = 3;
		
		//create a string array with one row per course and three columns
		String[][] scheduleString = new String[numRow][numCol];
		
		//iterate through schedule and fill each column with relevant data!
		for (int i = 0; i < numRow; i++) {
			
			scheduleString[i][0] = this.schedule.get(i).getName();
			
			scheduleString[i][1] = this.schedule.get(i).getSection();
			
			scheduleString[i][2] = this.schedule.get(i).getTitle();
		}
		
		return scheduleString;
	}
	
	/**
	 * Returns the wolf scheduler catalog in a 2D String array
	 * contains info such as course name, section, and title.
	 * Code is commented well but essentially this is performing the same
	 * tasks other methods with slight tweaks.
	 * 
	 * @return catalog catalog of courses in a 2D array
	 */
	public String[][] getCourseCatalog() {
		
		//number of rows is set by the number of courses in the catalog
		int numRow = this.courseCatalog.size();
		
		//no courses in the catalog means empty array
		if (numRow == 0) {
			return new String [0][0];
		}
		
		//three columns for name,title, and section
		int numCol = 3;
		
		//creating a string array with one row per course in the catalog
		//and three columns
		String[][] catalogString = new String[numRow][numCol];
		
		//iterate through the catalog, find the relevant and pertinent data for each course
		//then add the data to the 2D array
		for (int i = 0; i < numRow; i++) {
			//adding course name
			catalogString[i][0] = this.courseCatalog.get(i).getName();
			//adding the course section to the array
			catalogString[i][1] = this.courseCatalog.get(i).getSection();
			//adding the course title to array
			catalogString[i][2] = this.courseCatalog.get(i).getTitle();
			//for loop will continue to add data for the next courses in the catalog
		}
		
		return catalogString;
	}
	
	/**
	 * Creates a 2D array that is supposed to represent a schedule.
	 * Schedule will contain a lot of information such as course name, 
	 * course section, title, credits, instructorId, and meeting days.
	 * 
	 * 
	 * @return 2D array of the schedule
	 */
	public String[][] getFullScheduledCourses() {
		
		int numRow = this.schedule.size();
		
		if (numRow == 0) {
			return new String[0][0];
		}
		
		//six columns for all the different data about to be added 
		//to scheduler (name, section, title, credit hours, instructor id,
		//and meeting times)
		int numCol = 6;
		
		String[][] fullScheduleString = new String[numRow][numCol];
		
		//iterate through array and add to schedule
		for (int i = 0; i < numRow; i++) {
			
			//adds course name to schedule
			fullScheduleString[i][0] = this.schedule.get(i).getName();
			
			//adds section to schedule
			fullScheduleString[i][1] = this.schedule.get(i).getSection();
			
			//adds title to schedule
			fullScheduleString[i][2] = this.schedule.get(i).getTitle();
			
			//adds credit hours to schedule
			fullScheduleString[i][3] = "" + this.schedule.get(i).getCredits();
			
			
			//adds instructor id to schedule
			fullScheduleString[i][4] = this.schedule.get(i).getInstructorId();
			
			//adds meeting times to schedule
			fullScheduleString[i][5] = this.schedule.get(i).getMeetingString();
		}
		return fullScheduleString;
	}
	
	/**
	 * Gets schedule title
	 * @return schedule title
	 */
	public String getScheduleTitle() {
		
		return this.title;
	}
	
	/**
	 * Exports schedule to a file
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
	 * Gets course from catalog using name and section number
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return the course object corresponding to name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		//iterate through course catalog
		for (int i = 0; i < this.courseCatalog.size(); i++) {
			
			//check to see if current course has the same name and section
			//as input name and section
			if (this.courseCatalog.get(i).getName().equals(name) && 
					this.courseCatalog.get(i).getSection().equals(section)) {
				
				return this.courseCatalog.get(i);
			}
		}
		
		return null;
	}

	/**
	 * Adds course to schedule if course passes a couple of parameters set 
	 * in the method.
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
		//if tests above pass then added to schedule
		this.schedule.add(this.schedule.size(), this.getCourseFromCatalog(name, section));
		return true;
		
	}
	
	/**
	 * Removes course from schedule
	 * 
	 * @param name name of course
	 * @param section section of course
	 * @return true or false depending on if course exists or does not exist in schedule
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		
		//iterates through schedule
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
