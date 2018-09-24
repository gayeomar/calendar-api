package com.omar.calendar.repository;

import com.omar.calendar.domain.entity.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "select * from EVENT where CALENDAR_NAME = :name", nativeQuery = true)
    List<Event> findByCalendarName(@Param(value = "name") String name);

}