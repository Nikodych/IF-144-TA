package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

@Builder
@Data
public class GroupEntity {
    private String name;
    private SpecialityEntity speciality;
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
