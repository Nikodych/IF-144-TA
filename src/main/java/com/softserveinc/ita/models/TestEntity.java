package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestEntity {

    private String name;
    private String subject;
    private String numberOfTasks;
    private String amountOfTime;
    private String numberOfAttempts;
}