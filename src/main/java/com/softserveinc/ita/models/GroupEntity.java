package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

@Builder
@Data
@JsonDeserialize(builder = GroupEntity.GroupEntityBuilder.class)
public class GroupEntity {
    @JsonProperty("group_id")
    @EqualsAndHashCode.Exclude
    private String id;
    @JsonProperty("group_name")
    private String name;
    @JsonProperty("speciality_id")
    private SpecialityEntity speciality;
    @JsonProperty("faculty_id")
    private FacultyEntity faculty;

    public static GroupEntity getNewValidGroup(SpecialityEntity speciality, FacultyEntity faculty) {
        // name could be string, max length 10
        // in order to make it unique it will be 'test' + some random numbers
        var randCode = getRandomStringWithNumbers(6);
        var name = "test" + randCode;

        return GroupEntity
                .builder()
                .name(name)
                .speciality(speciality)
                .faculty(faculty)
                .build();
    }
}
