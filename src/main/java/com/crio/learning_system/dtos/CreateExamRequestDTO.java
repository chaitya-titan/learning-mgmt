package com.crio.learning_system.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Data
public class CreateExamRequestDTO {
    @NonNull
    private String subjectId;
}
