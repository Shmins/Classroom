package com.classroom.main.controller;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDto;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTeachers() {
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);
        when(teacherService.getAllTeachers()).thenReturn(teachers);

        ResponseEntity<List<TeacherDto>> response = teacherController.getAllTeachers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    void getTeacherById() {
        Teacher teacher = new Teacher();
        when(teacherService.getTeacherById(1L)).thenReturn(teacher);

        ResponseEntity<TeacherDto> response = teacherController.getTeacherById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(teacherService, times(1)).getTeacherById(1L);
    }

    @Test
    void createTeacher() {
        CreateTeacherDTO createTeacherDTO = new CreateTeacherDTO();
        Teacher teacher = new Teacher();
        when(teacherService.createTeacher(createTeacherDTO)).thenReturn(teacher);

        ResponseEntity<TeacherDto> response = teacherController.createTeacher(createTeacherDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(teacherService, times(1)).createTeacher(createTeacherDTO);
    }

    @Test
    void updateTeacher() {
        TeacherDto teacherDto = new TeacherDto();
        Teacher teacher = new Teacher();
        when(teacherService.updateTeacher(1L, teacherDto)).thenReturn(teacher);

        ResponseEntity<TeacherDto> response = teacherController.updateTeacher(1L, teacherDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(teacherService, times(1)).updateTeacher(1L, teacherDto);
    }

    @Test
    void deleteTeacher() {
        doNothing().when(teacherService).deleteTeacher(1L);

        ResponseEntity<Void> response = teacherController.deleteTeacher(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(teacherService, times(1)).deleteTeacher(1L);
    }
}