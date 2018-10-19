package com.omar.calendar.domain.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "CALENDAR")
@Data @Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class Calendar implements Serializable {

    @Id private @NonNull String name;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="id", updatable = true)
    private @NonNull User user;

}


