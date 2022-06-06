package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectEntity {

    private String name;
    private String description;
}
