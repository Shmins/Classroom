package com.classroom.gerenciamento.Controller;

import com.classroom.gerenciamento.Model.DTO.TeacherDto;
import com.classroom.gerenciamento.Model.Teacher;
import com.classroom.gerenciamento.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private TeacherDto TeacherDto;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDto> teacherDtos = teachers.stream()
                .map(TeacherDto::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teacherDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        TeacherDto teacherDto = new TeacherDto(teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getPassword());
        return ResponseEntity.ok(teacherDto);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createExample(@Valid @RequestBody TeacherDto exampleDTO) {
        Teacher teacher = teacherService.createTeacher(exampleDTO);
        TeacherDto teacherDto = TeacherDto.convertToDTO(teacher);

        URI location = URI.create(String.format("/api/v1/Teacher/%d", teacherDto.getId()));
        return ResponseEntity.created(location).body(teacherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.updateTeacher(id, teacherDto);
        TeacherDto teacherDto1 = TeacherDto.convertToDTO(teacher);
        return ResponseEntity.ok(teacherDto1);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteTeacher(@PathVariable Long id) {
        teacherService.DeleteTeacher(id);
        return ResponseEntity.noContent().build();
    }


}
