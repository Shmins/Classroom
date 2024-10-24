package com.classroom.main.service;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDto;
import com.classroom.main.model.Teacher;
import com.classroom.main.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;
    private CreateTeacherDTO createTeacherDTO;
    private TeacherDto teacherDto;

    @BeforeEach
    void setUp() {
        teacher = new Teacher();
        teacher.setIdTeacher(3L);
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");
        teacher.setPassword("password");

        createTeacherDTO = new CreateTeacherDTO();
        createTeacherDTO.setName("John Doe");
        createTeacherDTO.setEmail("john.doe@example.com");
        createTeacherDTO.setPassword("password");

        teacherDto = new TeacherDto();
        teacherDto.setName("John Doe");
        teacherDto.setEmail("john.doe@example.com");
        teacherDto.setPassword("password");
    }

    @Test
    @DisplayName("Test getAllTeachers")
    void getAllTeachers() {
        when(teacherRepository.findAll()).thenReturn(List.of(teacher));

        List<Teacher> teachers = teacherService.getAllTeachers();

        assertEquals(1, teachers.size());
        assertEquals(teacher.getIdTeacher(), teachers.get(0).getIdTeacher());
        assertEquals(teacher.getName(), teachers.get(0).getName());
        assertEquals(teacher.getEmail(), teachers.get(0).getEmail());
        assertEquals(teacher.getPassword(), teachers.get(0).getPassword());
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getTeacherById")
    void getTeacherById() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher foundTeacher = teacherService.getTeacherById(1L);

        assertNotNull(foundTeacher);
        assertEquals(teacher.getIdTeacher(), foundTeacher.getIdTeacher());
        verify(teacherRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test getTeacherById - Not Found")
    void getTeacherById_NotFound() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            teacherService.getTeacherById(1L);
        });

        assertEquals("Teacher with id 1 does not exist", exception.getMessage());
        verify(teacherRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test createTeacher")
    void createTeacher() {
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher createdTeacher = teacherService.createTeacher(createTeacherDTO);

        assertNotNull(createdTeacher);
        assertEquals(teacher.getName(), createdTeacher.getName());
        assertEquals(teacher.getEmail(), createdTeacher.getEmail());
        assertEquals(teacher.getPassword(), createdTeacher.getPassword());

        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    @DisplayName("Test updateTeacher")
    void updateTeacher() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher updatedTeacher = teacherService.updateTeacher(1L, teacherDto);

        assertNotNull(updatedTeacher);
        assertEquals(teacher.getName(), updatedTeacher.getName());
        assertEquals(teacher.getEmail(), updatedTeacher.getEmail());
        assertEquals(teacher.getPassword(), updatedTeacher.getPassword());
        verify(teacherRepository, times(1)).findById(1L);
        verify(teacherRepository, times(1)).save(any(Teacher.class));
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    @DisplayName("Test updateTeacher - Not Found")
    void updateTeacher_NotFound() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            teacherService.updateTeacher(1L, teacherDto);
        });

        assertEquals("Teacher with id 1 does not exist", exception.getMessage());
        verify(teacherRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test deleteTeacher")
    void deleteTeacher() {
        doNothing().when(teacherRepository).deleteById(1L);

        teacherService.deleteTeacher(1L);

        verify(teacherRepository, times(1)).deleteById(1L);
    }
}