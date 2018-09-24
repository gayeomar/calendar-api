package com.omar.calendar.business;

import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.repository.RepositoryDelegate;
import com.omar.calendar.util.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * A delegate to handle business pertaining to the Calendar entity.
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 * September 24, 2018
 *
 */
@Service
public class CalendarBusinessDelegate {

    @Autowired private RepositoryDelegate repository;

    @Autowired private CalendarUtil calendarUtil;

    //TODO: Add Aspect logging on top of each method

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<CalendarTO> createCalendar( CalendarTO calendarTo){
        CalendarTO toCreated = repository.createCalendar(calendarTo);
        return buildCreatedResponse(toCreated);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<CalendarTO> readCalendar(long userId){
        CalendarTO toCreated = repository.readCalendar(userId);
        return buildOKResponse(toCreated);
    }

    private ResponseEntity<CalendarTO> buildOKResponse(CalendarTO to){
        return ResponseEntity.ok(to);
    }

    private ResponseEntity<CalendarTO> buildCreatedResponse(CalendarTO to){
        return ResponseEntity.status(HttpStatus.CREATED).body(to);
    }
}
