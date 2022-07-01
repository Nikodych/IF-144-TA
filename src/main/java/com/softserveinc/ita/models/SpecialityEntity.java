package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

@Builder
@Data
@JsonDeserialize(builder = SpecialityEntity.SpecialityEntityBuilder.class)
public class SpecialityEntity {
    @JsonProperty("speciality_id")
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
