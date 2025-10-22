package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_USER;

import com.omar.calendar.business.UserBusinessDelegate;
import com.omar.calendar.domain.to.UserTO;
import com.omar.calendar.repository.CalendarRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * User API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@RestController
@RequestMapping(path = BASE_URL_USER)
@Tag(name = "User", description = "User API")
@Slf4j
public class UserController {

    @Autowired
    private UserBusinessDelegate delegate;

    @Autowired
    private CalendarRepository calendarRepository;

    @Operation(summary = "Read User by email", description = "Read a User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving a User"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving a User")})
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserTO> getUser(@PathVariable String email) {

        log.debug("Reading User with email: {}", email);

        return delegate.readUser(email);
    }


    @Operation(summary = "Create User", description = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while creating a User"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating a User")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserTO>  createUser(@RequestBody UserTO userTo) {

        log.debug("Creating a User from: {}", userTo);

        return delegate.createUser(userTo);
    }

}
