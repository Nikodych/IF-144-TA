package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerEntity {
    private String textOfAnswer;
    private boolean isAnswerRight;
}