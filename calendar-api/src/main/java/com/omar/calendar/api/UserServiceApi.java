package com.omar.calendar.api;


import com.omar.calendar.domain.to.UserTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * User API
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Tag(name = "User", description = "User API")
public interface UserServiceApi {

    @Operation(summary = "Read User by email", description = "Read a User by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while retrieving a User"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while retrieving a User")})
    UserTO getUser(String email);

    @Operation(summary = "Create User", description = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "An exception occurred while creating a User"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred while creating a User")})
    UserTO createUser(UserTO userTo);

}
