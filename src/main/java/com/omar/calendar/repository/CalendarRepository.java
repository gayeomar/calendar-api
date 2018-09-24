package com.omar.calendar.repository;

import com.omar.calendar.domain.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalendarRepository extends JpaRepository<Calendar, String> {

    @Query(value = "select * from CALENDAR where USER_ID = :id", nativeQuery = true)//@Query(value = "select cal from Calendar cal where cal.user.id = :id", nativeQuery = true)
    Calendar findByUserId(@Param(value = "id") long id);

    //TODO: Create DTO AND a repo for USER_API_KEY table. BAD, very BAD but use it as plan B for now.
    //This will retrieve a Calendar Id from a User api_key
    @Query(value = "select cal.name from CALENDAR cal where cal.user_id = (select USER_ID from USER_API_KEY where api_key = :apiKey)", nativeQuery = true)
    String findNameByApiKey(@Param(value = "apiKey") String apiKey);

}