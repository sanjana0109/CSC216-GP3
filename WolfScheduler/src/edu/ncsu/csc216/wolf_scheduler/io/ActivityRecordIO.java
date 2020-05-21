package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * The purpose of this class is to write an array list of Activity objects to
 * the provided file name.
 * 
 * @author Sanjana Cheerla
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Activity objects to the file name provided
	 * 
	 * @param fileName file to save to
	 * @param courses  list of course to save
	 * @throws IOException if the file cannot be written
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (Activity c : courses) {
			fileWriter.println(c.toString());
		}

		fileWriter.close();
	}

}