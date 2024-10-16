package com.classroom.main.controller.dto;


import com.classroom.main.model.Classroom;
import com.classroom.main.model.Utils.Turn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private String name_class;
    private Turn Turn;
    private Long id_teacher;
    private Long id;
    private String School_segment;

    public static ClassroomDTO convertToDTO(Classroom classroom){
        ClassroomDTO dto = new ClassroomDTO();
        dto.setName_class(classroom.getName_class());
        dto.setTurn(classroom.getTurn());
        dto.setId(classroom.getId());
        dto.setSchool_segment(classroom.getSchool_segment());
        return dto;
    }
}