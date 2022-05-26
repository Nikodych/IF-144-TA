package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.*;

public class AddingFacultyModal {

    public boolean isAddFacultyFormIsDisplayed() {
        return $x("//mat-dialog-container")
                .should(visible, ofSeconds(5))
                .isDisplayed();
    }
}
