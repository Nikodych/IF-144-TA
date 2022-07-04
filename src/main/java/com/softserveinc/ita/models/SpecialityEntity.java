package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

@Jacksonized
@Builder
@Data
public class SpecialityEntity {
    @JsonProperty(value = "speciality_id", access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private String id;
    @JsonProperty("speciality_code")
    private String code;
    @JsonProperty("speciality_name")
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
