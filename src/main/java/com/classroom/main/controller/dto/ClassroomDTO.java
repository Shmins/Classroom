package com.classroom.main.controller.dto;


import com.classroom.main.model.Classroom;
import com.classroom.main.model.Teacher;
import com.classroom.main.model.Utils.Turn;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassroomDTO {
    private String name_class;
    private Turn Turn;
    private Long idTeacher;
    private Long id;
    private String School_segment;

    public static ClassroomDTO convertToDTO(Classroom classroom, Teacher teacher){
        ClassroomDTO dto = new ClassroomDTO();
        dto.setName_class(classroom.getName_class());
        dto.setTurn(classroom.getTurn());
        dto.setId(classroom.getId());
        dto.setSchool_segment(classroom.getSchool_segment());
        teacher.setIdTeacher(classroom.getId_teacher().getIdTeacher());
        return dto;
    }
}