# Scheduler (Google calendar API integration)

Scheduler shows how to integrate google calendar API in your next project. 
I am providing here a basic functionality for creating and deleting new invites.
A method for showing how to pull holidays using google holidays calendar is provided as well.
Domain objects here can be changed to match your business problem.

*ICalendar interface:*
```
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
```

**Stack**

* Spring Boot (https://spring.io/projects/spring-boot)
* Java
* Maven
* Google calendar API (https://developers.google.com/calendar/quickstart/java)

**Global Settings**

Google calendar settings can be set up in the following .properties:

* resources/application-default.properties (development) 
* resources/application-unitTest.properties (Test cases). 

```
CALENDARHELPER_GSUITE_SERVICE_ACCOUNT_ID=
CALENDARHELPER_GSUITE_P12FILE=
CALENDARHELPER_GSUITE_ACCOUNT=
CALENDARHELPER_GSUITE_CALENDARID=
CALENDARHELPER_GSUITE_HOLIDAYSCALENDARID=
CALENDARHELPER_GSUITE_APPLICATION_NAME=
```
It's highly recommended the use ot ENV variables to configure your API keys to deploy on production

**Unit tests**
src/test/java/com/scheduler/test
