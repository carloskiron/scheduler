package com.scheduler.test;

import com.scheduler.core.GoogleCalendarHelper;
import com.scheduler.core.ICalendarHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestServiceConfig {

    @Bean
    ICalendarHelper getCalendarHelper() {
        return new GoogleCalendarHelper();
    }

}
