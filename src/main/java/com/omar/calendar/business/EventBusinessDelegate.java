package com.omar.calendar.business;

import com.omar.calendar.domain.entity.Event;
import com.omar.calendar.domain.to.EventTO;
import com.omar.calendar.repository.EventRepository;
import com.omar.calendar.repository.RepositoryDelegate;
import com.omar.calendar.util.CalendarConstant;
import com.omar.calendar.util.CalendarUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * A delegate to handle business pertaining to the Event entity.
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 *
 * September 24, 2018
 *
 */
@Service
@Slf4j
public class EventBusinessDelegate {

    @Autowired private RepositoryDelegate repositoryDelegate;

    @Autowired private CalendarUtil calendarUtil;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<EventTO> createEvent( EventTO eventTo){
        EventTO toCreated = repositoryDelegate.createEvent(eventTo);
        return buildCreatedResponse(toCreated);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<List<EventTO>> readEvents(String name){
        List<EventTO> toList  = repositoryDelegate.readEvents(name);
        return buildOKResponse(toList);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<List<EventTO>> readEventsByDate(String apiKey, String  dateStr){

        //Validate dateStr pattern
        doVanillaDateValidation(dateStr);

        //Get User Calendar name
        String calendarName = repositoryDelegate.getCalendarNameByApiKey(apiKey);

        //Get all events
        List<EventTO> allEvents = repositoryDelegate.getAllEvents();
        if(allEvents == null){
            return buildOKResponse(new ArrayList<EventTO>());
        }

        //Narrow it down to given date events
        List<EventTO> givenDateEvents = allEvents.stream()
                .filter(it -> it.getEventDate().equals(dateStr))
                .collect(Collectors.toList());
        if(givenDateEvents == null){
            return buildOKResponse(new ArrayList<EventTO>());
        }

        //Narrow it down to current user Calendar
        //Note: can be combined with above filter
        List<EventTO> userEvents = givenDateEvents.stream()
                .filter(it -> it.getCalendarName().equalsIgnoreCase(calendarName))
                .collect(Collectors.toList());

        return buildOKResponse(userEvents);
    }

    private ResponseEntity<List<EventTO>> buildOKResponse(List<EventTO> toList){
        return ResponseEntity.ok(toList);
    }

    private ResponseEntity<EventTO> buildCreatedResponse(EventTO to){
        return ResponseEntity.status(HttpStatus.CREATED).body(to);
    }

    /**
     * Validate a date pattern. Throws an exception if the pattern is invalid.
     *
     * @param dateStr The date -in string format- to validate
     */
    private void doVanillaDateValidation(String dateStr){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(CalendarConstant.CALENDAR_DATE_PATTERN);
            Date date = sdf.parse(dateStr);
            log.error("Bad date: {}", dateStr);
        }catch (Exception catchAll){
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}
