package com.classroom.main.controller;

import com.classroom.main.controller.dto.ClassroomDTO;
import com.classroom.main.controller.dto.CreateClassroomDTO;
import com.classroom.main.model.Classroom;
import com.classroom.main.model.Teacher;
import com.classroom.main.service.ClassroomService;
import com.classroom.main.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.classroom.main.model.Utils.Turn.Morning;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClassroomControllerTest {

    @Mock
    private ClassroomService classroomService;

    @InjectMocks
    private ClassroomController classroomController;

    private CreateClassroomDTO responseDto;
    private ClassroomDTO dto;

    private Classroom classroom = new Classroom();

    @Mock
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        responseDto = new CreateClassroomDTO();
        responseDto.setId_teacher(1L);
        responseDto.setName_class("Classroom 1");
        responseDto.setTurn(Morning);
        responseDto.setSchool_segment("Elementary");

        dto = new ClassroomDTO();
        dto.setId(1L);
        dto.setName_class("Classroom 1");
        dto.setTurn(Morning);
        dto.setSchool_segment("Elementary");
    }

    @Test
    void getAllClassrooms_returnsListOfClassroomDTOs() {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1L);

        Classroom classroom1 = new Classroom();
        classroom1.setId(1L);
        classroom1.setId_teacher(teacher);

        when(classroomService.getAllClassrooms()).thenReturn(List.of(classroom1));
        ResponseEntity<List<ClassroomDTO>> response = classroomController.getAllClassrooms();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(classroomService, times(1)).getAllClassrooms();

    }

    @Test
    void getClassroomById_withValidId_returnsClassroomDTO() {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1L);

        Classroom classroom = new Classroom();
        classroom.setId(1L);
        classroom.setId_teacher(teacher);

        when(classroomService.getClassroomById(1L)).thenReturn(classroom);

        Optional<Classroom> foundClassroom = Optional.ofNullable(classroomService.getClassroomById(1L));

        assertTrue(foundClassroom.isPresent());
        assertNotNull(foundClassroom.get().getId_teacher());
        assertEquals(1L, foundClassroom.get().getId());

        verify(classroomService, times(1)).getClassroomById(1L);
    }

    @Test
    void getClassroomById_withInvalidId_returnsNotFound() {
        when(classroomService.getClassroomById(anyLong())).thenThrow(new IllegalStateException("Classroom with id 1 does not exist"));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            classroomController.getClassroomById(1L);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
    }

    @Test
    void createClassroom_withValidData_createsClassroomSuccessfully() {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1L);

        Classroom classroom = new Classroom();
        classroom.setId(1L);
        classroom.setId_teacher(teacher);

        when(teacherService.getTeacherById(anyLong())).thenReturn(teacher);
        when(classroomService.createClassroom(any(CreateClassroomDTO.class))).thenReturn(classroom);

        ResponseEntity<ClassroomDTO> response = classroomController.createClassroom(responseDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(classroomService, times(1)).createClassroom(any(CreateClassroomDTO.class));
    }

    @Test
    void createClassroom_withInvalidTeacherId_throwsException() {
        responseDto.setId_teacher(999L);
        when(teacherService.getTeacherById(anyLong())).thenThrow(new IllegalStateException("Teacher with id 999 does not exist"));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            classroomController.createClassroom(responseDto);
        });

        assertTrue(exception.getMessage().contains("Teacher with id 999 does not exist"));
    }

    @Test
    void shouldThrowsExceptionWhenTeacherIsInClassroom(){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(1L);

        Classroom classroom = new Classroom();
        classroom.setId(1L);
        classroom.setId_teacher(teacher);

        when(teacherService.getTeacherById(anyLong())).thenReturn(teacher);
        when(classroomService.createClassroom(any(CreateClassroomDTO.class))).thenThrow(new IllegalStateException("Teacher is already in a classroom"));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            classroomController.createClassroom(responseDto);
        });

        assertTrue(exception.getMessage().contains("Teacher is already in a classroom"));

    }

    @Test
    void updateClassroom_withValidData_updatesClassroomSuccessfully() {
        Teacher Teacher = new Teacher();
        Teacher.setIdTeacher(1L);

        Classroom classroom = new Classroom();
        classroom.setId(1L);
        classroom.setId_teacher(Teacher);


        when(classroomService.updateClassroom(anyLong(), any(ClassroomDTO.class))).thenReturn(new Classroom(classroom));

        ResponseEntity<ClassroomDTO> response = classroomController.updateClassroom(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(classroomService, times(1)).updateClassroom(anyLong(), any(ClassroomDTO.class));
    }

    @Test
    void updateClassroom_withNonExistentId_throwsException() {
        when(classroomService.updateClassroom(anyLong(), any(ClassroomDTO.class))).thenThrow(new IllegalStateException("Classroom with id 1 does not exist"));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            classroomController.updateClassroom(1L, dto);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
    }

    @Test
    void deleteClassroom_withValidId_deletesClassroomSuccessfully() {
        doNothing().when(classroomService).deleteClassroom(anyLong());

        ResponseEntity<Void> response = classroomController.deleteClassroom(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteClassroom_withNonExistentId_throwsException() {
        doThrow(new IllegalStateException("Classroom with id 1 does not exist")).when(classroomService).deleteClassroom(anyLong());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            classroomController.deleteClassroom(1L);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
    }
}