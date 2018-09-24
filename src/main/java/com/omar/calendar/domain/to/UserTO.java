package com.omar.calendar.domain.to;

import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserTO implements Serializable {

    private final long id;

    private final @NonNull String firstName;

    private final @NonNull String lastName;

    private final @NonNull String email;

}