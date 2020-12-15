package com.scheduler.domain;

/**
 * class used to define details about an interview
 *
 * @author carlosmontoya
 */
public class InterviewDetail implements Cloneable {

    private static final String[] MONTHS = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private static final String[] DAYS = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    private int day;
    private int month;
    private int year;
    private int dayOfWeek;
    private int from;
    private int fromMinutes;
    private int to;
    private int toMinutes;
    private boolean remainderOfInterviewApplicantSMS = false;
    private boolean followUpInterviewApplicantSMS = false;
    private boolean reminderInterviewManager = false;
    private boolean followUpInterviewManager = false;
    private boolean reminderInterviewDayBeforeApplicant = false;
    private boolean reminderInterviewApplicant = false;
    private boolean followUpInterviewApplicant = false;
    private int interviewNumber = 1; //default is the very first interview
    private String inviteLink;
    private InterviewStatus status;
    private String calendarEventId;
    private String title;
    private String description;
    private String type;

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public boolean isRemainderOfInterviewApplicantSMS() {
        return remainderOfInterviewApplicantSMS;
    }

    public void setRemainderOfInterviewApplicantSMS(boolean remainderOfInterviewApplicantSMS) {
        this.remainderOfInterviewApplicantSMS = remainderOfInterviewApplicantSMS;
    }

    public boolean isFollowUpInterviewApplicantSMS() {
        return followUpInterviewApplicantSMS;
    }

    public void setFollowUpInterviewApplicantSMS(boolean followUpInterviewApplicantSMS) {
        this.followUpInterviewApplicantSMS = followUpInterviewApplicantSMS;
    }

    public boolean isReminderInterviewManager() {
        return reminderInterviewManager;
    }

    public void setReminderInterviewManager(boolean reminderInterviewManager) {
        this.reminderInterviewManager = reminderInterviewManager;
    }

    public boolean isFollowUpInterviewManager() {
        return followUpInterviewManager;
    }

    public void setFollowUpInterviewManager(boolean followUpInterviewManager) {
        this.followUpInterviewManager = followUpInterviewManager;
    }

    public boolean isReminderInterviewDayBeforeApplicant() {
        return reminderInterviewDayBeforeApplicant;
    }

    public void setReminderInterviewDayBeforeApplicant(boolean reminderInterviewDayBeforeApplicant) {
        this.reminderInterviewDayBeforeApplicant = reminderInterviewDayBeforeApplicant;
    }

    public boolean isReminderInterviewApplicant() {
        return reminderInterviewApplicant;
    }

    public void setReminderInterviewApplicant(boolean reminderInterviewApplicant) {
        this.reminderInterviewApplicant = reminderInterviewApplicant;
    }

    public boolean isFollowUpInterviewApplicant() {
        return followUpInterviewApplicant;
    }

    public void setFollowUpInterviewApplicant(boolean followUpInterviewApplicant) {
        this.followUpInterviewApplicant = followUpInterviewApplicant;
    }

    public String getToHourAsAmPm() {
        return convertToAmPmFormat(to, toMinutes);
    }

    public String getFromHourAsAmPm() {
        return convertToAmPmFormat(from, fromMinutes);
    }

    private String convertToAmPmFormat(int hour, int minutes) {

        String minutesAsString;
        if (minutes < 10) {
            minutesAsString = "0" + minutes;
        } else {
            minutesAsString = "" + minutes;
        }

        if (hour >= 0 && hour < 12) {
            return hour + ":" + minutesAsString + "am";
        } else if (hour >= 12 && hour < 13) {
            return hour + ":" + minutesAsString + "pm";
        } else {
            return (hour - 12) + ":" + minutesAsString + "pm";
        }

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(int fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public int getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(int toMinutes) {
        this.toMinutes = toMinutes;
    }

    public String getInterviewDateAsString() {

        String interviewDate = DAYS[dayOfWeek - 1] + ", " + MONTHS[month - 1] + " " + day;
        return interviewDate;
    }

    public int getInterviewNumber() {
        return interviewNumber;
    }

    public void setInterviewNumber(int interviewNumber) {
        this.interviewNumber = interviewNumber;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public String getFromStringDate(String timeZoneOffset) {

        String date = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + "T" + (from < 10 ? "0" + from : from) + ":" + (fromMinutes < 10 ? "0" + fromMinutes : fromMinutes) + ":00" + timeZoneOffset;
        return date;
    }

    public String getToStringDate(String timeZoneOffset) {
        String date = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + "T" + (to < 10 ? "0" + to : to) + ":" + (toMinutes < 10 ? "0" + toMinutes : toMinutes) + ":00" + timeZoneOffset;
        return date;
    }


    public InterviewStatus getStatus() {
        return status;
    }

    public void setStatus(InterviewStatus status) {
        this.status = status;
    }

    public String getCalendarEventId() {
        return calendarEventId;
    }

    public void setCalendarEventId(String calendarEventId) {
        this.calendarEventId = calendarEventId;
    }
}
