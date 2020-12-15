package com.scheduler.domain;

import java.util.ArrayList;
import java.util.List;

public class InterviewCalendarInvite {

    private String title;
    private String description;
    private String location;
    private String timeZone = "America/New_York";
    private String timeZoneOffset;
    private InterviewDetail interviewDetail;
    private List<String> emails = new ArrayList<>();

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

    public InterviewDetail getInterviewDetail() {
        return interviewDetail;
    }

    public void setInterviewDetail(InterviewDetail interviewDetail) {
        this.interviewDetail = interviewDetail;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(String timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }
}
