package com.scheduler.core;

import com.scheduler.domain.InterviewCalendarInvite;

import java.util.List;

public interface ICalendarHelper {

    /**
     * Send a calendar invitation using the information provided
     *
     * @param interviewInvite
     * @return
     * @throws Exception
     */
    String scheduleInterview(InterviewCalendarInvite interviewInvite) throws Exception;

    /**
     * method for getting the list of holidays
     *
     * @return
     * @throws Exception
     */
    List<Long> getHolidays() throws Exception;

    /**
     * method for deleting a calendar invite
     *
     * @param eventId
     * @return
     * @throws Exception
     */
    String deleteInterview(String eventId)throws Exception;

}
