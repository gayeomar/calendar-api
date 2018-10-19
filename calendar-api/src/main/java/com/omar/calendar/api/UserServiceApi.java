package com.omar.calendar.api;


import com.omar.calendar.domain.to.UserTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * User API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Api(tags = {"User"})
public interface UserServiceApi {

    @ApiOperation(value = "Read User by email;", notes = "Read a User by email", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrieved successfully", response = UserTO.class),
            @ApiResponse(code = 400, message = "An exception occurred while retrieving a User", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while retrieving a User")})
    UserTO getUser(String email);

    @ApiOperation(value = "Create User;", notes = "Create a new User", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully", response = String.class),
            @ApiResponse(code = 400, message = "An exception occurred while creating a User", response = Error.class),
            @ApiResponse(code = 500, message = "An internal server error occurred while creating a User")})
    UserTO createUser(UserTO userTo);

}
