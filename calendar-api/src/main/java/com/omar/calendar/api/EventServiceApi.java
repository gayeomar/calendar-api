package com.omar.calendar.api;


import com.omar.calendar.domain.to.EventTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

/**
 * Event API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Tag(name = "Event", description = "Event API")
public interface EventServiceApi {

    @Operation(summary = "Read Calendar Event", description = "Read a Calendar Event by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving a calendar event"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving a calendar event")})
    List<EventTO> readEvents(String name);

    @Operation(summary = "Create Calendar Event", description = "Create a new Calendar Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Calendar event created successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while creating a calendar event"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating a calendar event")})
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
    @Operation(summary = "Read current User Calendar Event by date", description = "Read current User Calendar Event by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar events retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving calendar events"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving calendar events")})
    List<EventTO> getEventByDate(String dateStr, final String apiKey);

}
