package com.omar.calendar.domain.to;

import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

@Data
public class CalendarTO implements Serializable {

    private final @NonNull  String name;

    private final @NonNull  UserTO user;
}
