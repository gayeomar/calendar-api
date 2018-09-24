package com.omar.calendar.endpoint;


import static com.omar.calendar.util.CalendarConstant.BASE_URL_USER;

import com.omar.calendar.business.UserBusinessDelegate;
import com.omar.calendar.domain.to.UserTO;
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
 * User API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@RestController
@RequestMapping(path = BASE_URL_USER)
@Api(tags = {"User"})
@Slf4j
public class UserController {

    @Autowired
    private UserBusinessDelegate delegate;

    @Autowired
    private CalendarRepository calendarRepository;

    @ApiOperation(value = "Read User by email;", notes = "Read a User by email", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrieved successfully", response = UserTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a User", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a User")})
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserTO> getUser(@PathVariable String email) {

        log.debug("Reading User with email: {}", email);

        return delegate.readUser(email);
    }


    @ApiOperation(value = "Create User;", notes = "Create a new User", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a User", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a User")})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserTO>  createUser(@RequestBody UserTO userTo) {

        log.debug("Creating a User from: {}", userTo);

        return delegate.createUser(userTo);
    }

}
