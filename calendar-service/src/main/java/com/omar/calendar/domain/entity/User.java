package com.omar.calendar.domain.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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