package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.softserveinc.ita.pageobjects.util.RandomUtil.getRandomNumber;

@Builder
@Getter
@Setter
public class GroupEntity {
    private String name;
    private SpecialityEntity speciality;
    private String faculty = "Інститут інформаційних технологій";

    public static String getNewValidName() {
        // name could be string, max length 10
        // in order to make it unique it will be 'test' + some random numbers
        var randCode = getRandomNumber(6);

        return "test" + randCode;
    }
}
