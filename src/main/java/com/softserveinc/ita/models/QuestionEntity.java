package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Builder
@Data
public class QuestionEntity {
    private String textOfQuestion;
    private int typeOfQuestion;
    private int levelOfQuestion;
    private File file;
    private AnswerEntity answer;
}