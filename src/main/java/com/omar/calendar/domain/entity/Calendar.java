package com.omar.calendar.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "CALENDAR")
@Data @Accessors(fluent = true)
public class Calendar implements Serializable {

    @Id private final String name;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="id", updatable = true)
    private final @NonNull User user;

}


