package com.omar.calendar.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USER")
@Data @Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    //private final @Id @Setter(AccessLevel.PRIVATE)long id;
    private @Id long id;

    private @NonNull String firstName;

    private @NonNull String lastName;

    private @NonNull String email;

}