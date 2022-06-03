package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class DateTimeRange {
    private LocalDate start;
    private LocalDate end;
}