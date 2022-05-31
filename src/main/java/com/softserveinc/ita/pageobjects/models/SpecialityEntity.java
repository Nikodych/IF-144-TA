package com.softserveinc.ita.pageobjects.models;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import static com.softserveinc.ita.pageobjects.util.RandomUtil.getRandomNumber;

@Data
public class SpecialityEntity {
    private String code;
    private String name;

    public SpecialityEntity() {
        var randCode = getRandomNumber(5);
        this.code = Integer.toString(randCode); // only numbers, no more than 5 symbols;
        this.name = "test" + randCode;
    }
}
