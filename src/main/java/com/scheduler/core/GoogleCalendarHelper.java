package com.scheduler.core;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.scheduler.domain.InterviewCalendarInvite;
import com.scheduler.domain.InterviewDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class GoogleCalendarHelper implements ICalendarHelper {

    @Value("${CALENDARHELPER_GSUITE_APPLICATION_NAME}")
    private String APPLICATION_NAME;
    @Value("${CALENDARHELPER_GSUITE_CALENDARID}")
    private String CALENDAR_ID;
    @Value("${CALENDARHELPER_GSUITE_HOLIDAYSCALENDARID}")
    private String HOLIDAYS_CALENDAR_ID;
    @Value("${CALENDARHELPER_GSUITE_SERVICE_ACCOUNT_ID}")
    private String GSUITE_SERVICE_ACCOUNT_ID;
    @Value("${CALENDARHELPER_GSUITE_P12FILE}")
    private String GSUITE_P12FILE;
    @Value("${CALENDARHELPER_GSUITE_ACCOUNT}")
    private String GSUITE_ACCOUNT;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Credential getCredentialsFromServiceAccount(final NetHttpTransport HTTP_TRANSPORT) throws Exception {

        File p12File = new File(GSUITE_P12FILE);

        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .setServiceAccountId(GSUITE_SERVICE_ACCOUNT_ID)
                .setServiceAccountPrivateKeyFromP12File(p12File)
                .setServiceAccountScopes(SCOPES)
                .setServiceAccountUser(GSUITE_ACCOUNT)
                .build();

        return credential;
    }


    @Override
    public String scheduleInterview(InterviewCalendarInvite interviewInvite) throws Exception {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentialsFromServiceAccount(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary(interviewInvite.getTitle())
                .setLocation(interviewInvite.getLocation())
                .setDescription(interviewInvite.getDescription());

        String timeZone = interviewInvite.getTimeZone();
        InterviewDetail detail = interviewInvite.getInterviewDetail();
        java.util.Calendar calendar = java.util.Calendar.getInstance(TimeZone.getTimeZone(timeZone));

        calendar.set(detail.getYear(), detail.getMonth() - 1, detail.getDay(), detail.getFrom(), detail.getFromMinutes(), 0);
        Date startDateTime = calendar.getTime();
        EventDateTime start = new EventDateTime()
                .setDateTime(new DateTime(startDateTime)).setTimeZone(timeZone);
        event.setStart(start);

        calendar.set(detail.getYear(), detail.getMonth() - 1, detail.getDay(), detail.getTo(), detail.getToMinutes(), 0);
        Date endDateTime = calendar.getTime();
        EventDateTime end = new EventDateTime()
                .setDateTime(new DateTime(endDateTime)).setTimeZone(timeZone);
        event.setEnd(end);

        EventAttendee[] attendees = new EventAttendee[interviewInvite.getEmails().size()];
        int i = 0;
        for (String email : interviewInvite.getEmails()) {
            attendees[i++] = new EventAttendee().setEmail(email);
        }
        event.setAttendees(Arrays.asList(attendees));
        event = service.events().insert(CALENDAR_ID, event).setSendUpdates("all").execute();
        return event.getId();
    }

    @Override
    public String deleteInterview(String eventId) throws Exception {
        
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentialsFromServiceAccount(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();


        service.events().delete(CALENDAR_ID, eventId).execute();
        return "DELETED";
    }

    public List<Long> getHolidays() throws Exception {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentialsFromServiceAccount(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        List<Event> events = service.events().list(HOLIDAYS_CALENDAR_ID).execute().getItems();
        List<Long> result = new ArrayList<>();
        for (Event event : events) {
            long milliSeconds = event.getStart().getDate().getValue();
            result.add(milliSeconds);
        }

        return result;

    }
}
