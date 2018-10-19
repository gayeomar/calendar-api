package com.omar.calendar.domain.to;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AttendeesTO implements Serializable {

    List<UserTO> attendees;

}
