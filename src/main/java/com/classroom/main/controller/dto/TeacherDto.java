package com.classroom.gerenciamento.Model.DTO;


import com.classroom.gerenciamento.Model.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Teacher", description = "Teacher data transfer object")
public class TeacherDto implements Serializable {
    Long Id;
    String Name;
    String Email;
    String Password;


    public TeacherDto convertToDTO(Teacher teacher) {
        return new TeacherDto(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getPassword());
    }
}

