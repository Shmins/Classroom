package com.classroom.main.service;

import com.classroom.main.controller.dto.ClassroomDTO;
import com.classroom.main.controller.dto.CreateClassroomDTO;
import com.classroom.main.model.Classroom;
import com.classroom.main.model.Teacher;
import com.classroom.main.repository.ClassroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.classroom.main.model.Utils.Turn.Morning;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceTest {

    @Mock
    private ClassroomRepository repo;

    @InjectMocks
    private ClassroomService service;

    private Classroom classroom;
    private CreateClassroomDTO responseDto;
    private ClassroomDTO dto;

    @Mock
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        classroom = new Classroom();
        classroom.setId(1L);
        classroom.setName_class("Classroom 1");
        classroom.setId_teacher(new Teacher());
        classroom.setTurn(Morning);
        classroom.setSchool_segment("Elementary");

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
    void getAllClassrooms_returnsAllClassrooms() {
        when(repo.findAll()).thenReturn(List.of(new Classroom(classroom)));

        List<Classroom> classrooms = service.getAllClassrooms();

        assertEquals(1, classrooms.size());
        verify(repo).findAll();
    }

    @Test
    void getClassroomById_withValidId_returnsClassroom() {
        when(repo.findById(anyLong())).thenReturn(Optional.of(new Classroom(classroom)));

        Classroom foundClassroom = service.getClassroomById(1L);

        assertNotNull(foundClassroom);
        assertEquals(classroom.getId(), foundClassroom.getId());
        assertEquals(classroom.getName_class(), foundClassroom.getName_class());
        assertEquals(classroom.getTurn(), foundClassroom.getTurn());
        assertEquals(classroom.getSchool_segment(), foundClassroom.getSchool_segment());
        assertEquals(classroom.getId_teacher(), foundClassroom.getId_teacher());


        verify(repo).findById(anyLong());
    }

    @Test
    void getClassroomById_withInvalidId_throwsException() {
        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.getClassroomById(1L);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
        verify(repo).findById(anyLong());
    }

    @Test
    void createClassroom_withValidData_createsClassroomSuccessfully() {
        when(repo.save(any(Classroom.class))).thenReturn(new Classroom(classroom));

        Classroom createdClassroom = service.createClassroom(responseDto);

        assertNotNull(createdClassroom);
        assertEquals(classroom.getName_class(), createdClassroom.getName_class());
        assertEquals(classroom.getTurn(), createdClassroom.getTurn());
        assertEquals(classroom.getSchool_segment(), createdClassroom.getSchool_segment());
        assertEquals(classroom.getId_teacher(), createdClassroom.getId_teacher());

        verify(repo).save(any(Classroom.class));
    }

    @Test
    void createClassroom_withInvalidTeacherId_throwsException() {
        responseDto.setId_teacher(999L);
        when(teacherService.getTeacherById(anyLong())).thenThrow(new IllegalStateException("Teacher with id 999 does not exist"));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.createClassroom(responseDto);
        });

        assertTrue(exception.getMessage().contains("Teacher with id 999 does not exist"));
        verify(repo, never()).save(any(Classroom.class));
    }

    @Test
    void updateClassroom_withValidData_updatesClassroomSuccessfully() {
        when(repo.findById(anyLong())).thenReturn(Optional.of(new Classroom(classroom)));
        when(repo.save(any(Classroom.class))).thenReturn(new Classroom(classroom));

        Classroom updatedClassroom = service.updateClassroom(1L, dto);

        assertNotNull(updatedClassroom);
        assertEquals(classroom.getId_teacher(), updatedClassroom.getId_teacher());
        assertEquals(classroom.getName_class(), updatedClassroom.getName_class());
        assertEquals(classroom.getSchool_segment(), updatedClassroom.getSchool_segment());
        assertEquals(classroom.getTurn(), updatedClassroom.getTurn());

        verify(repo).findById(anyLong());
        verify(repo).save(any(Classroom.class));
    }

    @Test
    void updateClassroom_withNonExistentId_throwsException() {
        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.updateClassroom(1L, dto);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
        verify(repo, never()).save(any(Classroom.class));
    }



    @Test
    void deleteClassroom_withNonExistentId_throwsException() {
        when(repo.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.deleteClassroom(1L);
        });

        assertTrue(exception.getMessage().contains("Classroom with id 1 does not exist"));
        verify(repo, never()).deleteById(anyLong());
    }
}