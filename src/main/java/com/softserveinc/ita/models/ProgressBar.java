package com.softserveinc.ita.models;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class ProgressBar {

    private static final SelenideElement progressBar = $x("//mat-progress-bar");

    public static void waitForDisappear() {
        progressBar.should(disappear);
    }

    public static void waitForAppear() {
        progressBar.should(appear, ofSeconds(3));
    }
}