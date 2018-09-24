package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_CALENDAR;

import com.omar.calendar.business.CalendarBusinessDelegate;
import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.repository.CalendarRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(tags = {"Calendar"})
@Slf4j
public class CalendarController {
    @Autowired
    private CalendarBusinessDelegate delegate;

    @Autowired
    private CalendarRepository repository;

    @ApiOperation(value = "Read Calendar", notes = "Read a Calendar by user Id", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Calendar retrieved successfully", response = CalendarTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a Calendar", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a Calendar")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CalendarTO> getUser(@PathVariable long id) {

        log.debug("Reading Calendar for User with id: {}", id);

        return delegate.readCalendar(id);
    }


    @ApiOperation(value = "Create Calendar;", notes = "Create a new Calendar", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Calendar created successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a Calendar", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a Calendar")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CalendarTO>  createUser(@RequestBody CalendarTO calendarTo) {

        log.debug("Creating a User from: {}", calendarTo);

        return delegate.createCalendar(calendarTo);
    }

}
