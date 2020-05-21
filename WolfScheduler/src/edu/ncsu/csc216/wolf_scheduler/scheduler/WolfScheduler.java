package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler class maintains an array list of courses from the provided file
 * and also a schedule for a student. This class is in charge of maintaining
 * records of adding, removing and displaying Courses, Events and in the
 * Schedule arrayList.
 * 
 * @author Sanjana Cheerla
 *
 */
public class WolfScheduler {

	/** Title of the schedule */
	private String title;

	/** The schedule of Activity objects */
	private ArrayList<Activity> schedule;

	/** The catalog of courses */
	private ArrayList<Course> catalog;

	/** The number of rows used in getCourseCatalog() and getScheduledCourses() */
	private static final int SCHEDULE_COURSE_INDEX = 4;

	/** The number of rows used in getFullScheduledCourses() */
	private static final int FULL_SCHEDULE_COURSE_INDEX = 7;

	/**
	 * Constructs a WolfScheduler Object, title is initialized to "My Schedule". A
	 * new scheduled is initialized to an ArrayList of courses. A catalog of
	 * courses, consisting of an Array List, is made from the given file name
	 * parameter. If there is an error regarding the fileName, an
	 * IllegalArgumentException is thrown with the message "Cannot find file"
	 * 
	 * @param fileName The file name for course records
	 * @throws IllegalArgumentException If the file cannot be accessed, with the
	 *                                  message "Cannot find file"
	 */
	public WolfScheduler(String fileName) {
		try {
			setTitle("My Schedule");
			catalog = CourseRecordIO.readCourseRecords(fileName);
			schedule = new ArrayList<Activity>();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file");
		}
	}

	/**
	 * Searches for a Course with the given name and section in the catalog and if
	 * it is found, returns the course that has that name and section. If no such
	 * course exists, null is returned.
	 * 
	 * @param name    The name of the Course being found
	 * @param section The section of the Course being found
	 * @return the Course if it is found, null if there is no such Course in the
	 *         catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {

		for (int i = 0; i < catalog.size(); i++) {
			if (((Course) catalog.get(i)).getName().equals(name)
					&& ((Course) catalog.get(i)).getSection().equals(section)) {
				return (Course) catalog.get(i);
			}
		}

		return null;
	}

	/**
	 * Returns the title of the schedule
	 * 
	 * @return the title of the schedule
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the schedule. If the title is null, an
	 * IllegalArgumentException is thrown with the message "Title cannot be null".
	 * 
	 * @param title The title to set for the schedule
	 * @throws IllegalArgumentException If the title is null.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

	/**
	 * Organizes the catalog ArrayList into a 2D array consisting of columns for the
	 * Course name, section, title, and meeting string in that order.
	 * 
	 * @return String[][] 2D String array of the courses available into one row for
	 *         each course, and in the columns, one for the name, one for the title,
	 *         and one for the section number.
	 */
	public String[][] getCourseCatalog() {
		if (this.catalog.isEmpty()) {
			return new String[0][0];
		}
		String[][] courseCatalog = new String[this.catalog.size()][SCHEDULE_COURSE_INDEX];
		for (int i = 0; i < courseCatalog.length; i++) {
			int index = 0;
			courseCatalog[i][index] = catalog.get(i).getShortDisplayArray()[index];
			index++;
			courseCatalog[i][index] = catalog.get(i).getShortDisplayArray()[index];
			index++;
			courseCatalog[i][index] = catalog.get(i).getShortDisplayArray()[index];
			index++;
			courseCatalog[i][index] = catalog.get(i).getShortDisplayArray()[index];
		}
		return courseCatalog;
	}

	/**
	 * Organizes the schedule ArrayList into a 2D array consisting of 4 columns. If
	 * the activity in the schedule is a Course, the columns contain the Course
	 * name, section, title, and meeting string in that order. If the activity in
	 * the schedule is a Event, the columns contain an empty String, empty String,
	 * event title, and event meeting string in that order.
	 * 
	 * @return String[][] 2D String array of the courses and events in the student's
	 *         schedule. If the activity is a Course the columns contain the Course
	 *         name, section, title, and meeting string in that order. If the
	 *         activity is a Event, the columns contain an empty String, empty
	 *         String, Activity title, and Activity meeting string in that order.
	 */
	public String[][] getScheduledActivities() {
		if (this.schedule.isEmpty()) {
			return new String[0][0];
		}
		String[][] scheduleCatalog = new String[this.schedule.size()][SCHEDULE_COURSE_INDEX];
		for (int i = 0; i < scheduleCatalog.length; i++) {
			int index = 0;
			scheduleCatalog[i][index] = schedule.get(i).getShortDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getShortDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getShortDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getShortDisplayArray()[index];
		}
		return scheduleCatalog;
	}

	/**
	 * Organizes the schedule ArrayList into a 2D array. If the Activity in the
	 * schedule is a Course, the columns contain Course name, section, title,
	 * credits, instructorId, meeting string, and an empty string. If the Activity
	 * in the schedule is an Event, the columns contain an empty String, empty
	 * String, Event title, empty String, emptyString, meeting string, and event
	 * details in that order.
	 * 
	 * @return String[][] 2D String array of the Activities in the student'
	 *         schedule. If the Activity in the schedule is a Course, the columns
	 *         contain Course name, section, title, credits, instructorId, meeting
	 *         string, and an empty string. If the Activity in the schedule is an
	 *         Event, the columns contain an empty String, empty String, Event
	 *         title, empty String, emptyString, meeting string, and event details
	 *         in that order.
	 */
	public String[][] getFullScheduledActivities() {
		if (this.schedule.isEmpty()) {
			return new String[0][0];
		}
		String[][] scheduleCatalog = new String[this.schedule.size()][FULL_SCHEDULE_COURSE_INDEX];
		for (int i = 0; i < scheduleCatalog.length; i++) {
			int index = 0;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
			index++;
			scheduleCatalog[i][index] = schedule.get(i).getLongDisplayArray()[index];
		}
		return scheduleCatalog;
	}

	/**
	 * Exports the current schedule to the given file.
	 * 
	 * @param fileName The name of the file that the activities is being written to
	 * @throws IllegalArgumentException With the message "The file cannot be saved"
	 *                                  if an IOException is caught.
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

	/**
	 * Adds the specified course to the student schedule. If the course is already
	 * added in the schedule an exception is thrown. If the course does not exist in
	 * the catalog the method returns false. If the course exists and is not in the
	 * student schedule. the course is added to the schedule and a value of true is
	 * returned.
	 * 
	 * @param name    The name of the course to be added
	 * @param section The section number of the course to be added
	 * @return true if the schedule has been added to the schedule, and false if the
	 *         course is not in the course catalog
	 * @throws IllegalArgumentException With the message "You are already enrolled
	 *                                  in " if the course with the same name exists
	 *                                  in the student schedule or with the message
	 *                                  "The course cannot be added due to a
	 *                                  conflict." if there is a conflict between
	 *                                  the course being added and the activities
	 *                                  already in the schedule.
	 */
	public boolean addCourse(String name, String section) {
		if (this.getCourseFromCatalog(name, section) == null) {
			return false;
		}

		Course c = this.getCourseFromCatalog(name, section);

		for (int i = 0; i < this.schedule.size(); i++) {

			if (c.isDuplicate(this.schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}

			try {
				this.schedule.get(i).checkConflict(c);
			} catch (ConflictException conflictE) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			
		}

		this.schedule.add(c);
		return true;

	}

	/**
	 * Adds the specified Event to the student schedule. If the Event is already
	 * added in the schedule an exception is thrown.
	 * 
	 * @param title        the tile of the event being added.
	 * @param meetingDays  the meeting days of the event being added.
	 * @param startTime    the start time of the event being added.
	 * @param endTime      the end time of the event being added.
	 * @param weeklyRepeat how often the event is repeated.
	 * @param eventDetails the details of the event.å
	 * @throws IllegalArgumentException if the event being added already exists in
	 *                                  the schedule or with the message "The event
	 *                                  cannot be added due to a conflict." if there
	 *                                  is a conflict between the event being added
	 *                                  and the activities already in the schedule.
	 */
	public void addEvent(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat,
			String eventDetails) {
		Event e = new Event(title, meetingDays, startTime, endTime, weeklyRepeat, eventDetails);
		for (int i = 0; i < this.schedule.size(); i++) {

			if (e.isDuplicate(this.schedule.get(i))) {
				throw new IllegalArgumentException("You have already" + " created an event called " + e.getTitle());
			}

			try {
				this.schedule.get(i).checkConflict(e);
			} catch (ConflictException conflictE) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}

		}

		this.schedule.add(e);
	}

	/**
	 * Removes an activity specified by the index parameter. If the index is less
	 * than 0 or greater than the size or the size of the schedule is 0, the
	 * activity does not exist and therefore cannot be removed and the method
	 * returns false. If the parameter index is a valid index in the Schedule, it is
	 * removed and true is returned.
	 * 
	 * @param idx the index of the activity being removed.
	 * @return true if the object at the index is removed. False if the schedule is
	 *         empty, or the index is out of bounds of the schedule.
	 */
	public boolean removeActivity(int idx) {
		if (schedule.size() == 0) {
			return false;
		}
		if (idx < 0 || idx >= schedule.size()) {
			return false;
		}

		this.schedule.remove(idx);
		return true;
	}

	/**
	 * Resets the schedule by removing all elements and renaming the title of the
	 * schedule to "My Schedule".
	 */
	public void resetSchedule() {
		schedule.removeAll(schedule);
		setTitle("My Schedule");
	}

}
