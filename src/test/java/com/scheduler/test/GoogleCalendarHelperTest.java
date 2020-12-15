package com.scheduler.test;

import com.scheduler.core.ICalendarHelper;
import com.scheduler.domain.InterviewCalendarInvite;
import com.scheduler.domain.InterviewDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import reactor.util.Assert;

import java.util.List;
import java.util.TimeZone;

@SpringJUnitConfig(TestServiceConfig.class)
@TestPropertySource("/application-unitTest.properties")
@WebAppConfiguration
@SpringBootApplication

public class GoogleCalendarHelperTest {

    @Autowired
    private ICalendarHelper calendarHelper;

    @Value("${TEST_ACCOUNT_EMAIL}")
    private String TEST_ACCOUNT_EMAIL;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    public void testGetHolidays() throws Exception {

        List<Long> result = calendarHelper.getHolidays();
        System.out.println(result.size());
        Assert.notEmpty(result);
    }

    @org.junit.jupiter.api.Test
    public void testCreateAndDeleteInvite() throws Exception {

            InterviewDetail interviewDetail = new InterviewDetail();
            interviewDetail.setInterviewNumber(1);
            interviewDetail.setDay(17);
            interviewDetail.setMonth(4);
            interviewDetail.setYear(2020);
            interviewDetail.setFrom(7);
            interviewDetail.setFromMinutes(7);
            interviewDetail.setFromMinutes(0);
            interviewDetail.setTo(8);
            interviewDetail.setToMinutes(0);

            InterviewCalendarInvite invite = new InterviewCalendarInvite();
            invite.setTimeZone("America/New_York");

            int offSet = (((TimeZone.getTimeZone(invite.getTimeZone()).getRawOffset() / 1000) / 60) / 60);
            String symbol = offSet < 0 ? "-" : "";
            offSet = Math.abs(offSet);

            invite.setTimeZoneOffset(symbol + (offSet < 10 ? "0" + offSet : offSet) + ":00");
            invite.setTitle("My New New Test interview");
            invite.getEmails().add(TEST_ACCOUNT_EMAIL);
            invite.setDescription("Test y mas test");
            invite.setLocation("my new address");
            invite.setInterviewDetail(interviewDetail);

            String eventId = calendarHelper.scheduleInterview(invite);
            Assert.notNull(eventId);
            System.out.println(eventId);

            Thread.sleep(10000);
            String deleted = calendarHelper.deleteInterview(eventId);
            Assert.isTrue(deleted != null && deleted.equalsIgnoreCase("DELETED"));

    }
}
