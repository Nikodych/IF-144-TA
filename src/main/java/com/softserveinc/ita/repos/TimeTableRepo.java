package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.models.TimeTableEntity;

public class TimeTableRepo {

    public static TimeTableEntity getTimeTableForSubject(SubjectEntity subject) {
        return TimeTableEntity
                .builder()
                .groupId("4")
                .subjectId(subject.getId())
                .startDate("2022-07-15")
                .startTime("10:00:00")
                .endDate("2022-07-15")
                .endTime("12:00:00")
                .build();
    }

    public static TimeTableEntity getUpdatedTimeTableForSubject(TimeTableEntity oldTimeTable, SubjectEntity subject) {
        return TimeTableEntity
                .builder()
                .id(oldTimeTable.getId())
                .groupId("3")
                .subjectId(subject.getId())
                .startDate("2022-07-15")
                .startTime("10:00:00")
                .endDate("2022-07-15")
                .endTime("12:00:00")
                .build();
    }
}
