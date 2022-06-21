package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeTableEntity {
    @JsonProperty("timetable_id")
    private int id;
    @JsonProperty("subject_id")
    private int subjectId;

    public TimeTableEntity() {
    }

    public TimeTableEntity(int id, int subjectId) {
        this.id = id;
        this.subjectId = subjectId;
    }
}
