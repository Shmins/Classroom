<<<<<<< Updated upstream
package com.classroom.gerenciamento.Controller;

import com.classroom.gerenciamento.Model.DTO.TeacherDto;
import com.classroom.gerenciamento.Model.Teacher;
import com.classroom.gerenciamento.Service.TeacherService;
=======
package com.classroom.main.controller;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDTO;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.TeacherService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
>>>>>>> Stashed changes
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
<<<<<<< Updated upstream
public class TeacherController {

    private final TeacherService teacherService;
    private TeacherDto TeacherDto;
=======
@Tag(name = "Teacher", description = "Teacher API")
public class TeacherController {
    private final TeacherService teacherService;
>>>>>>> Stashed changes

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
<<<<<<< Updated upstream

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


=======
    
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<TeacherDTO> dto = teachers.stream()
                .map(TeacherDTO::convetToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id){
        Teacher teacher = teacherService.getTeacherById(id);
        TeacherDTO dto = TeacherDTO.convetToDTO(teacher);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody CreateTeacherDTO teacherDTO){
        Teacher teachers = teacherService.createTeacher(teacherDTO);
        TeacherDTO responseDTO = TeacherDTO.convetToDTO(teachers);

        URI location = URI.create(String.format("/api/v1/teacher/%d", teachers.getId()));

        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO){
        Teacher teacher = teacherService.updateTeacher(id, teacherDTO);
        TeacherDTO responseDTO = TeacherDTO.convetToDTO(teacher);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
>>>>>>> Stashed changes
}
