package com.classroom.main.controller.dto;

import com.classroom.main.model.Teacher;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static TeacherDTO convetToDTO(Teacher teacher){
        TeacherDTO dto = new TeacherDTO();
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setPassword(teacher.getPassword());
        dto.setCreatedDate(teacher.getCreatedDate());
        dto.setUpdatedDate(teacher.getUpdatedDate());
        return dto;
    }
}
