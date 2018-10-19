package com.omar.calendar.domain.to;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CalendarTO implements Serializable {

    private @NonNull  String name;

    private @NonNull  UserTO user;
}
