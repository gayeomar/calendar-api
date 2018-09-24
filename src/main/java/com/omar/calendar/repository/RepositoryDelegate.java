package com.omar.calendar.repository;

import com.omar.calendar.domain.entity.Calendar;
import com.omar.calendar.domain.entity.Event;
import com.omar.calendar.domain.entity.User;
import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.domain.to.EventTO;
import com.omar.calendar.domain.to.UserTO;
import com.omar.calendar.util.CalendarUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryDelegate {

    @Autowired private EventRepository eventRepository;

    @Autowired private UserRepository userRepository;

    @Autowired private CalendarRepository calendarRepository;

    @Autowired private CalendarUtil calendarUtil;

    public CalendarTO createCalendar( CalendarTO calendarTo){
        Calendar entityCreated = calendarRepository.save(calendarUtil.toEntity(calendarTo));
        return calendarUtil.fromEntity(entityCreated);
    }

    public CalendarTO readCalendar(long userId){
        Calendar entity = calendarRepository.findByUserId(userId);
        return calendarUtil.fromEntity(entity);
    }

    public UserTO createUser( UserTO userTo){
        User entity = userRepository.save(calendarUtil.toEntity(userTo));
        return calendarUtil.fromEntity(entity);
    }

    public UserTO readUser(String email){
        User entity = userRepository.findByEmailIgnoreCaseContaining(email);
        return calendarUtil.fromEntity(entity);
    }

    public EventTO createEvent( EventTO eventTo){
        Event entity = calendarUtil.toEntity(eventTo);
        Event entityCreated = eventRepository.save(entity);
        return calendarUtil.fromEntity(entityCreated);
    }

    public List<EventTO> readEvents(String name){
        List<Event> entities = eventRepository.findByCalendarName(name);
        return calendarUtil.fromEntity(entities);
    }

    public List<EventTO> getAllEvents(){
        return calendarUtil.fromEntity(eventRepository.findAll());
    }

    public String getCalendarNameByApiKey(String apiKey){
        return calendarRepository.findNameByApiKey(apiKey);
    }




}
