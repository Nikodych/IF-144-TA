package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

@Builder
@Data
public class SpecialityEntity {
    private String code;
    private String name;

    public static SpecialityEntity getNewValidSpeciality() {
        var randCode = getRandomStringWithNumbers(5);
        var name = "test" + randCode;

        return SpecialityEntity
                .builder()
                .code(randCode)
                .name(name)
                .build();
    }
}
