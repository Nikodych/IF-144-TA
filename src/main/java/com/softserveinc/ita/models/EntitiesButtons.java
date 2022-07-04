package com.softserveinc.ita.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntitiesButtons {
    DASHBOARD_PAGE("dashboard","",""),
    FACULTIES_PAGE("faculties","Faculties", "Факультети"),
    GROUPS_PAGE("group", "Groups", "Групи" ),
    SPECIALITIES_PAGE("speciality", "Specialties", "Спеціальності"),
    SUBJECTS_PAGE("subjects","Subjects","Предмети"),
    RESULTS_PAGE("results","Results","Результати"),
    PROTOCOL_PAGE("protocol","Protocol","Протокол"),
    STUDENTS_PAGE("","Students","Студенти"),
    ADMINS_PAGE("admin-user","Admins","Адміни"),
    ABOUT_US_PAGE("aboutUs","","");

    private final String pageName;
    private final String cardTitleEng;
    private final String cardTitleUa;
}