package com.classroom.main.controller;

import com.classroom.main.controller.dto.ClassroomDTO;
import com.classroom.main.controller.dto.CreateClassroomDTO;
import com.classroom.main.model.Classroom;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.ClassroomService;
import com.classroom.main.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Classroom", description = "Classroom API")
@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;
    private final TeacherService teacherService;

    public ClassroomController(ClassroomService classroomService, TeacherService teacherService) {
        this.classroomService = classroomService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        List<ClassroomDTO> dto = classrooms.stream()
                .map(classroom -> ClassroomDTO.convertToDTO(classroom, classroom.getId_teacher()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.getClassroomById(id);
        ClassroomDTO dto = ClassroomDTO.convertToDTO(classroom, classroom.getId_teacher());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClassroomDTO> createClassroom(@Valid @RequestBody CreateClassroomDTO classroomDTO) {
        Teacher teacher = teacherService.getTeacherById(classroomDTO.getId_teacher());
        Classroom classroom = new Classroom();
        classroom.setName_class(classroomDTO.getName_class());
        classroom.setSchool_segment(classroomDTO.getSchool_segment());
        classroom.setTurn(classroomDTO.getTurn());
        classroom.setId_teacher(teacher);
        classroom = classroomService.createClassroom(classroomDTO);
        ClassroomDTO responseDTO = ClassroomDTO.convertToDTO(classroom, teacher);

        URI location = URI.create(String.format("/api/v1/classroom/%d", classroom.getId()));

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable Long id, @Valid @RequestBody ClassroomDTO classroomDTO) {
        Classroom classroom = classroomService.updateClassroom(id, classroomDTO);
        ClassroomDTO responseDTO = ClassroomDTO.convertToDTO(classroom, classroom.getId_teacher());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}