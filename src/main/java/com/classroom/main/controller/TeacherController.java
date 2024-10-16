package com.classroom.main.controller;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDto;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Teacher", description = "Teacher API")
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDto> dto = teachers.stream()
                .map(TeacherDto::convetToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        TeacherDto dto = TeacherDto.convetToDTO(teacher);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@Valid @RequestBody CreateTeacherDTO teacherDTO) {
        Teacher teachers = teacherService.createTeacher(teacherDTO);
        TeacherDto responseDTO = TeacherDto.convetToDTO(teachers);

        URI location = URI.create(String.format("/api/v1/teacher/%d", teachers.getIdTeacher()));

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDto teacherDTO) {
        Teacher teacher = teacherService.updateTeacher(id, teacherDTO);
        TeacherDto responseDTO = TeacherDto.convetToDTO(teacher);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}