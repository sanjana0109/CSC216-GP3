package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class for WolfScheduler, use to create Event objects. An Event object
 * consists of the title, weekly repeat, event details, meeting days, start time
 * and end time. Extends the Activity class.
 * 
 * @author Sanjana Cheerla
 *
 */
public class Event extends Activity {

	/** Weekly repeat */
	private int weeklyRepeat;

	/** eventDetails */
	private String eventDetails;

	/** Minimum number of times a Event is repeated a week */
	private static final int MIN_WEEKLY_REPEAT = 1;

	/** Maximum number of times a Event is repeated a week */
	private static final int MAX_WEEKLY_REPEAT = 4;

	/** Length of short display array */
	private static final int SHORT_DISPLAY_LENGTH = 4;

	/** Length of long display array */
	private static final int LONG_DISPLAY_LENGTH = 7;

	/**
	 * Creates an Event object that has a title, meeting days, start time, end time,
	 * weekly repeat and event details.
	 * 
	 * @param title        the title of the Event
	 * @param meetingDays  the meeting days of the Event
	 * @param startTime    the start time of the Event
	 * @param endTime      the end time of the Event
	 * @param weeklyRepeat how often the Event repeats weekly
	 * @param eventDetails the details of the Event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		this.setWeeklyRepeat(weeklyRepeat);
		this.setEventDetails(eventDetails);
	}

	/**
	 * Returns how often the Event is repeated weekly
	 * 
	 * @return how often an Event is repeated weekly
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the weeklyRepeat variable. The weeklyRepeat is invalid if it is less
	 * than 1 or greater than 4 an and IllegalArugmentException is thrown.
	 * 
	 * @param weeklyRepeat the weeklyRepeat to set.
	 * @throws IllegalArgumentException if the weeklyRepeat is less than 1 or
	 *                                  greater than 4 with the message "Invalid
	 *                                  weekly repeat.".
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < MIN_WEEKLY_REPEAT || weeklyRepeat > MAX_WEEKLY_REPEAT) {
			throw new IllegalArgumentException("Invalid weekly repeat.");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Returns the details of the event
	 * 
	 * @return String of the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the eventDetails variable. If the eventDetails is null, it is invalid,
	 * and an IllegalArgumentException is thrown.
	 * 
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException with the message "Invalid event details." if
	 *                                  eventDetails is null.
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * This method uses the super class', Activity's getMeetingString and appends
	 * the weeklyRepeat variable.
	 * 
	 * @return String with every WeeklyRepeat weeks appended onto the Activity's
	 *         getMeetingString() method.
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + weeklyRepeat + " weeks)";
	}

	/**
	 * This method creates an array of length 4 that contains empty String, empty
	 * String, title, and meeting string in that order. The empty String are for
	 * fields that Course has but Event does not.
	 * 
	 * @return String[] of length 4 that contains empty String, emptyString, title
	 *         and meeting String
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[SHORT_DISPLAY_LENGTH];
		int index = 0;
		shortDisplay[index] = "";
		shortDisplay[++index] = "";
		shortDisplay[++index] = super.getTitle();
		shortDisplay[++index] = this.getMeetingString();
		return shortDisplay;
	}

	/**
	 * This method creates an array of length 7 that contains empty String, empty
	 * String, title, empty String, emptyString, meeting string, and event details
	 * in that order. The empty String are for fields that Course has but Event does
	 * not.
	 * 
	 * @return String[] of length 7 that contains empty String, empty String, title,
	 *         empty String, emptyString, meeting string, and event details in that
	 *         order.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[LONG_DISPLAY_LENGTH];
		int index = 0;
		longDisplay[index] = "";
		longDisplay[++index] = "";
		longDisplay[++index] = super.getTitle();
		longDisplay[++index] = "";
		longDisplay[++index] = "";
		longDisplay[++index] = this.getMeetingString();
		longDisplay[++index] = this.eventDetails;
		return longDisplay;
	}

	/**
	 * Returns a String using the super class, Activity, and Event to produce a list
	 * separated by a commas so it is displayed as followed:
	 * title,meetingDays,startTime,endTime,weeklyRepeat,eventDetails
	 * 
	 * @return String of fields using Activity's filed as well as Event's fields.
	 */
	@Override
	public String toString() {
		return super.getTitle() + "," + super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime()
				+ "," + this.getWeeklyRepeat() + "," + this.getEventDetails();
	}

	/**
	 * Sets the Event's meetingDays. If the meetingDays contains any other
	 * characters than "M,T,W,H,F,S,U", an IllegalArgumentException is thrown. An
	 * IllegalArgumentException is also thrown if meetingDays is null or empty.
	 * 
	 * @param meetingDays The meetingDays to set
	 * @throws IllegalArgumentException If meetingDays is null or empty, and if the
	 *                                  meetingDays are invalid meaning that it must
	 *                                  have "M" "T" "W" "T" "F" "S" "U" as a
	 *                                  combination of strings and no other
	 *                                  characters.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.isEmpty()) {
			throw new IllegalArgumentException();
		}

		boolean isValid = true;

		for (int i = 0; i < meetingDays.length(); i++) {
			if (!(meetingDays.charAt(i) == 'M') && !(meetingDays.charAt(i) == 'T') && !(meetingDays.charAt(i) == 'W')
					&& !(meetingDays.charAt(i) == 'H') && !(meetingDays.charAt(i) == 'F')
					&& !(meetingDays.charAt(i) == 'S') && !(meetingDays.charAt(i) == 'U')) {
				isValid = false;
				break;
			}
		}

		if (!isValid) {
			throw new IllegalArgumentException();
		}
		if (isValid) {
			super.setMeetingDays(meetingDays);
		}
	}

	/**
	 * Checks to see if the activity is a duplicate of the Event. The activity is a
	 * duplicate if it is an instance of Event and has the same title.
	 * 
	 * @param activity the activity to be checked if it is a duplicate
	 * @return true if the activity is a duplicate, false if it is not a duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event e = (Event) activity;
			if (this.getTitle().equals(e.getTitle())) {
				return true;

			}
		}
		return false;
	}

}
