package com.classroom.main.controller.dto;

import com.classroom.main.model.Utils.Turn;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClassroomDTO {
    @NotBlank(message = "NameClass is mandatory")
    private String name_class;

    @NotNull
    private Turn Turn;

    @NotBlank(message = "SchoolSegment is mandatory")
    private String School_segment;

    @NotNull(message = "Teacher is mandatory")
    private Long id_teacher;

}
