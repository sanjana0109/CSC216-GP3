package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * This class reads course records from files, process the file, and writes them
 * to another file using the Course Class, specifically the overwritten
 * toString() method to write them into the other file. Each of the courses are
 * put into an ArrayList of Course objects. This ArrayList is used to write the
 * Courses into another file when trying to write courses into another file.
 * 
 * @author Sanjana Cheerla
 */
public class CourseRecordIO {
	/**
	 * Generates an ArrayList of the course records read from the file. If there are
	 * any invalid courses in the file, they are ignored. If the file can't be found
	 * or accessed a File NotFoundException is thrown.
	 * 
	 * Source Method: GP1 Guided Task: Working with the Java Libraries Java
	 * Collections Framework
	 * 
	 * @param fileName The file to read Course records from
	 * @return an ArrayList of Course objects List of valid Courses from the file
	 *         provided
	 * @throws FileNotFoundException If the file can't be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Course> courses = new ArrayList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) && course.getSection().equals(c.getSection())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return courses;
	}

	/**
	 * Processes a line from the file and returns a constructed Course object from
	 * it.
	 * 
	 * @param line The line that is being processed
	 * @return Course Course gets constructed from the input parameter line
	 * @throws IllegalArgumentException If there are more characters than "A" and if
	 *                                  "A" exists in the line, and if the course is
	 *                                  in an invalid format. If Course is in an
	 *                                  invalid format.
	 */
	private static Course readCourse(String line) {

		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");

		try {
			String name = lineScanner.next();
			String title = lineScanner.next();
			String section = lineScanner.next();
			int credits = lineScanner.nextInt();
			String instructorId = lineScanner.next();
			String meetingDays = lineScanner.next();
			int startTime = 0;
			int endTime = 0;

			if (meetingDays.equals("A")) {
				if (lineScanner.hasNext()) {
					lineScanner.close();
					throw new IllegalArgumentException();
				} else {
					lineScanner.close();
					Course c = new Course(name, title, section, credits, instructorId, meetingDays);
					return c;
				}
			}

			startTime = lineScanner.nextInt();
			endTime = lineScanner.nextInt();
			lineScanner.close();
			Course c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			return c;
		}

		catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
	}

}