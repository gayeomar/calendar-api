package com.omar.calendar.api;

import com.omar.calendar.domain.to.CalendarTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;


/**
 * Calendar API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Api(tags = {"Calendar"})
public interface CalendarServiceApi {

    @ApiOperation(value = "Read Calendar", notes = "Read a Calendar by user Id", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Calendar retrieved successfully", response = CalendarTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a Calendar", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a Calendar")})
    ResponseEntity<CalendarTO> getCalendar(@ApiParam(name = "id", value = "id", required = true) long id);


    @ApiOperation(value = "Create Calendar;", notes = "Create a new Calendar", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Calendar created successfully", response = CalendarTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a Calendar", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a Calendar")})
    ResponseEntity<CalendarTO>  createCalendar(@ApiParam(name = "calendarTo", value = "calendarTo", required = true) CalendarTO calendarTo);
}
