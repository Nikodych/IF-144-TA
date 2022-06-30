package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeTableEntity {
    @JsonProperty("timetable_id")
    private String id;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("subject_id")
    private String subjectId;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("end_time")
    private String endTime;

    public TimeTableEntity() {
    }

    public TimeTableEntity(String id, String groupId, String subjectId, String startDate, String startTime, String endDate, String endTime) {
        this.id = id;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }
}
