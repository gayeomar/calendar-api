package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_EVENT;

import com.omar.calendar.business.EventBusinessDelegate;
import com.omar.calendar.domain.to.EventTO;
import com.omar.calendar.exception.CalendarException;
import com.omar.calendar.util.CalendarConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@Tag(name = "Event", description = "Event API")
@Slf4j
public class EventController {

    @Autowired
    private EventBusinessDelegate delegate;

    @Operation(summary = "Read Calendar Event", description = "Read a Calendar Event by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving a calendar event"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving a calendar event")})
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<EventTO>> readEvents(@PathVariable(value = "name") String name) {

        log.debug("Reading events for calendar with name: {}", name);

        return delegate.readEvents(name);
    }

    @Operation(summary = "Create Calendar Event", description = "Create a new Calendar Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Calendar event created successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while creating a calendar event"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating a calendar event")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EventTO>  createEvent(@Valid @RequestBody EventTO eventTo) throws CalendarException{
            //, final BindingResult result
        log.debug("Creating an Event from: {}", eventTo);

        /*if (result.hasErrors()) {
            throw new CalendarException("400", "BadRequest!!!", result.getFieldErrors());
        }*/

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
    @Operation(summary = "Read current User Calendar Event by date", description = "Read current User Calendar Event by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar events retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving calendar events"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving calendar events")})
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
