package com.omar.calendar.api;


import com.omar.calendar.domain.to.EventTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;

/**
 * Event API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Api(tags = {"Event"})
public interface EventServiceApi {

    @ApiOperation(value = "Read Calendar Event", notes = "Read a Calendar Event by Id", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Event retrieved successfully", response = EventTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a calendar event", response = EventTO.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a calendar event")})
    List<EventTO> readEvents(String name);

    @ApiOperation(value = "Create Calendar Event;", notes = "Create a new Calendar Event", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Calendar event created successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a calendar event", response = EventTO.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a calendar event")})
    EventTO  createEvent(EventTO eventTo);

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
            @ApiResponse(code = 400, message = "An exception occurred while retrieving calendar events", response = EventTO.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving calendar events")})
    List<EventTO> getEventByDate(String dateStr, final String apiKey);

}
