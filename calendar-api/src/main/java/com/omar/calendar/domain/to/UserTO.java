package com.omar.calendar.domain.to;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class UserTO implements Serializable {

    private long id;

    private @NonNull String firstName;

    private @NonNull String lastName;

    private @NonNull String email;

}