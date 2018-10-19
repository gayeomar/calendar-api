package com.omar.calendar.domain.to;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A DTO that showcase Lombok and Bean Validation. The end-point using this should
 * be annoted with <code>@Valid</code>
 *
 * @author <a href="mailto:gayeomar@hotmail.com">Omar Gaye</a>
 *
 * September 24, 2018
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class EventTO implements Serializable {

    /** Event Id */
    private Long id;

    /** The id of the calendar this event is attached to */
    @NotBlank(message = "Calendar cannot be null.")
    private @NonNull String calendarName;

    /** The event title */
    @NotBlank(message = "Title cannot be null.")
    private @NonNull String title;

    /** The event location */
    @NotBlank(message = "Location cannot be null.")
    private @NonNull String location;

    /** The event date, String format YYYY-MM-DD */
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message="Date pattern should be YYYY-MM-DD")
    private @NonNull String eventDate;

    /** The event start time, String format HH24-MM-SS */
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message="TIME pattern should be HH24-MM-SS")
    //TODO: PATTERN COULD BE IMPROVE, To enforce 24 or less for example.
    private @NonNull String startTime;

    /** The event end time, String format HH24-MM-SS */
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message="TIME pattern should be HH24-MM-SS")
    private @NonNull String endTime;

    /** The event attendees list as a comma separated user ids */
    private  @NonNull String attendees;

    /** The date and time the event reminder will is sent. String format HH24-MM-SS */
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message="TIME pattern should be HH24-MM-SS")
    private @NonNull String reminderTime;

    /** Indicates whether reminder was sent Y/N*/
    @Pattern(regexp = "Y|N")
    private @NonNull String isReminderSent;

}
