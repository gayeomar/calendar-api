package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_CALENDAR;

import com.omar.calendar.api.CalendarServiceApi;
import com.omar.calendar.business.CalendarBusinessDelegate;
import com.omar.calendar.client.UserClient;
import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.logging.AroundLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Calendar API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@RestController
@RequestMapping(path = BASE_URL_CALENDAR)
@Slf4j
public class CalendarServiceController implements CalendarServiceApi {

    private CalendarBusinessDelegate delegate;
    private UserClient userClient;

    public CalendarServiceController(CalendarBusinessDelegate delegate, UserClient userClient){

        this.delegate = delegate;
        this.userClient = userClient;
    }

    @Override
    @AroundLog
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalendarTO> getCalendar(@PathVariable long id) {

        log.debug("Reading Calendar for User with id: {}", id);

        log.info("testing Hystrix");
        userClient.getUser("omar@gmail.com");

        return delegate.readCalendar(id);
    }

    @Override
    @AroundLog
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalendarTO>  createCalendar(@RequestBody CalendarTO calendarTo) {

        return delegate.createCalendar(calendarTo);
    }

    @AroundLog
    @GetMapping(value = "/to/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CalendarTO getCalendarTo(@PathVariable long id) {

        String test = null;
        test.length();
        return delegate.readCalendarTo(id);
    }

}
