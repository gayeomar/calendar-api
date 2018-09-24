package com.omar.calendar.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER")
@Data @Accessors(fluent = true)
public class User implements Serializable {

    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    //private final @Id @Setter(AccessLevel.PRIVATE)long id;
    private final @Id long id;

    private final @NonNull String firstName;

    private final @NonNull String lastName;

    private final @NonNull String email;

}