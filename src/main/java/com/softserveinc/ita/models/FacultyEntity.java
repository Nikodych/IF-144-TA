package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FacultyEntity {
    private String name;
    private String description;
}
