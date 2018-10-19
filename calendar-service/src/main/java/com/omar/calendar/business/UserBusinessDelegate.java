package com.omar.calendar.business;

import com.omar.calendar.domain.to.UserTO;
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
 * A delegate to handle business pertaining to the User entity.
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 *
 * September 24, 2018
 *
 */
@Service
public class UserBusinessDelegate {

    @Autowired private RepositoryDelegate repositoryDelegate;

    @Autowired private CalendarUtil calendarUtil;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<UserTO> createUser( UserTO userTo){
        UserTO createdTo = repositoryDelegate.createUser(userTo);
        return buildCreatedResponse(createdTo);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRED)
    public ResponseEntity<UserTO> readUser(String email){
        UserTO userTo = repositoryDelegate.readUser(email);
        return buildOKResponse(userTo);
    }

    private ResponseEntity<UserTO> buildOKResponse(UserTO to){
        return ResponseEntity.ok(to);
    }

    private ResponseEntity<UserTO> buildCreatedResponse(UserTO to){
        return ResponseEntity.status(HttpStatus.CREATED).body(to);
    }

}
