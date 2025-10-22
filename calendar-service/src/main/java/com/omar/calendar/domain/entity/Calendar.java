package com.omar.calendar.domain.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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


