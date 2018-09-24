package com.omar.calendar.domain.to;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class AttendeesTO implements Serializable {

    List<UserTO> attendees;

}
