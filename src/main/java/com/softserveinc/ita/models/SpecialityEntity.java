package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Getter;

import static com.softserveinc.ita.util.RandomUtil.getRandomNumber;
import static java.lang.String.valueOf;

@Builder
@Getter
public class SpecialityEntity {
    private String code;
    private String name;

    public static SpecialityEntity getNewValidSpeciality() {
        var randCode = getRandomNumber(5);
        var code = valueOf(randCode); // only numbers, no more than 5 symbols;
        var name = "test" + randCode;

        return SpecialityEntity
                .builder()
                .code(code)
                .name(name)
                .build();
    }
}
