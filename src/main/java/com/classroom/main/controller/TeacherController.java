package com.classroom.main.controller;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDTO;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.TeacherService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDTO> dto = teachers.stream()
                .map(TeacherDTO::convetToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        TeacherDTO dto = TeacherDTO.convetToDTO(teacher);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody CreateTeacherDTO teacherDTO) {
        Teacher teachers = teacherService.createTeacher(teacherDTO);
        TeacherDTO responseDTO = TeacherDTO.convetToDTO(teachers);

        URI location = URI.create(String.format("/api/v1/teacher/%d", teachers.getId()));

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = teacherService.updateTeacher(id, teacherDTO);
        TeacherDTO responseDTO = TeacherDTO.convetToDTO(teacher);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}