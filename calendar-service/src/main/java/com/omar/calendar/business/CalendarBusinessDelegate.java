package com.omar.calendar.business;

import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.repository.RepositoryDelegate;
import com.omar.calendar.util.CalendarUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CalendarBusinessDelegate {

    private RepositoryDelegate repository;

    private CalendarUtil calendarUtil;

    public CalendarBusinessDelegate(CalendarUtil calendarUtil, RepositoryDelegate repository){
        this.calendarUtil = calendarUtil;
        this.repository = repository;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<CalendarTO> createCalendar( CalendarTO calendarTo){
        CalendarTO toCreated = repository.createCalendar(calendarTo);
        return buildCreatedResponse(toCreated);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<CalendarTO> readCalendar(long userId){
        log.debug("Calling calendar repository");
        CalendarTO toCreated = repository.readCalendar(userId);
        log.debug("Building OK response");
        return buildOKResponse(toCreated);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public CalendarTO readCalendarTo(long userId){
        return  repository.readCalendar(userId);
    }

    private ResponseEntity<CalendarTO> buildOKResponse(CalendarTO to){
        return ResponseEntity.ok(to);
    }

    private ResponseEntity<CalendarTO> buildCreatedResponse(CalendarTO to){
        return ResponseEntity.status(HttpStatus.CREATED).body(to);
    }
}
