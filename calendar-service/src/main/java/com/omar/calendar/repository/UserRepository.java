package com.omar.calendar.repository;

import com.omar.calendar.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailIgnoreCaseContaining(String email);
}
