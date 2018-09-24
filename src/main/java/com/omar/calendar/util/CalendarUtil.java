package com.omar.calendar.util;

import com.omar.calendar.domain.entity.Calendar;
import com.omar.calendar.domain.entity.Event;
import com.omar.calendar.domain.entity.User;
import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.domain.to.EventTO;
import com.omar.calendar.domain.to.UserTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public final class CalendarUtil {

    public User toEntity(UserTO to){
        return new User(to.getId(), to.getFirstName(), to.getLastName(), to.getEmail());
    }

    public UserTO fromEntity(User entity){
        if (entity == null){
            return  null;
        }
        return new UserTO(entity.id(), entity.firstName(), entity.lastName(), entity.email());
    }

    public Calendar toEntity(CalendarTO to){
        UserTO userTo = new UserTO(to.getUser().getId(), to.getUser().getFirstName(),
                to.getUser().getLastName(), to.getUser().getEmail());
        User userEntity = toEntity(userTo);
        return new Calendar(to.getName(), userEntity);
    }

    public CalendarTO fromEntity(Calendar entity){
        if (entity == null){
            return null;
        }

        UserTO userTo = fromEntity(entity.user());
        CalendarTO calendarTo = new CalendarTO(entity.name(), userTo);

        return  calendarTo;
    }

    public Event toEntity(EventTO to){
        return new Event(to.getCalendarName(), to.getTitle(), to.getLocation(),
                to.getEventDate(), to.getStartTime(), to.getEndTime(), to.getAttendees(),
                to.getReminderTime(), to.getIsReminderSent());
    }

    public List<EventTO> fromEntity(List<Event> entities){
        if (entities == null){
            return null;
        }

        List<EventTO> eventToList = new ArrayList<>();

        entities.stream().forEach(entity -> eventToList.add(fromEntity(entity)));

        return eventToList;
    }

    public EventTO fromEntity(Event entity){
        if (entity == null){
            return null;
        }

        return new EventTO(entity.id(), entity.calendarName(), entity.title(), entity.location(),
                entity.eventDate(), entity.startTime(), entity.endTime(), entity.attendees(),
                entity.reminderTime(), entity.isReminderSent());

    }



}
