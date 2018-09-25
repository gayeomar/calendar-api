package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_EVENT;

import com.omar.calendar.business.EventBusinessDelegate;
import com.omar.calendar.domain.to.EventTO;
import com.omar.calendar.util.CalendarConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Event API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@RestController
@RequestMapping(path = BASE_URL_EVENT)
@Api(tags = {"Event"})
@Slf4j
public class EventController {

    @Autowired
    private EventBusinessDelegate delegate;

    @ApiOperation(value = "Read Calendar Event", notes = "Read a Calendar Event by Id", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event retrieved successfully", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a calendar event", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a calendar event")})
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventTO>> readEvents(@PathVariable(value = "name") String name) {

        log.debug("Reading events for calendar with name: {}", name);

        return delegate.readEvents(name);
    }

    @ApiOperation(value = "Create Calendar Event;", notes = "Create a new Calendar Event", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Calendar event created successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a calendar event", response = EventTO.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a calendar event")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EventTO>  createEvent(@Valid @RequestBody EventTO eventTo) {

        log.debug("Creating an Event from: {}", eventTo);

        return delegate.createEvent(eventTo);
    }

    /**
     *
     * Retrieve events by date. Filter by API Key. key maps to User one-to-one.
     * An API Gateway can append the Key to the request header.
     *
     * @param dateStr The date
     * @param apiKey The User API Key
     * @return A list of calendar events visible to the caller
     */
    @ApiOperation(value = "Read current User Calendar Event by date;", notes = "Read current User Calendar Event by date", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Calendar events retrieved successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving calendar events", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving calendar events")})
    @GetMapping(value = "date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventTO>> getEventByDate(@PathVariable(value = "date") String dateStr,
            @RequestHeader(name = CalendarConstant.CALENDAR_API_KEY, required = true) final String apiKey) {

        log.debug("Reading calendar event for date: {} and usey api key {}", dateStr, apiKey);

        return delegate.readEventsByDate(apiKey, dateStr);
    }

    @GetMapping(value = "month/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventTO>> getEventByMonth(@PathVariable String monthStr) throws IllegalStateException{
        //Month format should be YYYY-MM
       throw new IllegalStateException("Not implemented yet :-( ");
    }

    @GetMapping(value = "day/{day}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventTO>> getEventByDay(@PathVariable String dayStr) throws IllegalStateException{
        //dayStr format should be YYYY-MM-DD, day will be extract
        throw new IllegalStateException("Not implemented yet :-( ");
    }


}
