package com.omar.calendar.api;

import com.omar.calendar.domain.to.CalendarTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;


/**
 * Calendar API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Tag(name = "Calendar", description = "Calendar API")
public interface CalendarServiceApi {

    @Operation(summary = "Read Calendar", description = "Read a Calendar by user Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calendar retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving a Calendar"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving a Calendar")})
    ResponseEntity<CalendarTO> getCalendar(@Parameter(name = "id", description = "id", required = true) long id);


    @Operation(summary = "Create Calendar", description = "Create a new Calendar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Calendar created successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while creating a Calendar"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating a Calendar")})
    ResponseEntity<CalendarTO>  createCalendar(@Parameter(name = "calendarTo", description = "calendarTo", required = true) CalendarTO calendarTo);
}
