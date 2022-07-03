package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionEntity {
    private String textOfQuestion;
    private int typeOfQuestion;
    private int levelOfQuestion;
    private AnswerEntity answer;
}