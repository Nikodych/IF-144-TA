package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;
import org.joda.time.LocalDateTime;

@Builder
@Data
public class DateTimeRange {
    private LocalDateTime start;
    private LocalDateTime end;
}
