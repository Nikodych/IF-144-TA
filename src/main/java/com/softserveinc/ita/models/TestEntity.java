package com.softserveinc.ita.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestEntity {
    @SerializedName("test_name")
    private String name;
    private transient String subject;
    @SerializedName("tasks")
    private String numberOfTasks;
    @SerializedName("time_for_test")
    private String amountOfTime;
    @SerializedName("attempts")
    private String numberOfAttempts;
}