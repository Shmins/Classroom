package com.classroom.main.service;

import com.classroom.main.controller.dto.ClassroomDTO;
import com.classroom.main.controller.dto.CreateClassroomDTO;
import com.classroom.main.model.Classroom;
import com.classroom.main.repository.ClassroomRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final TeacherService teacherService;
    private ClassroomDTO ClassroomDTO;

    public ClassroomService(ClassroomRepository classroomRepository, TeacherService teacherService) {
        this.classroomRepository = classroomRepository;
        this.teacherService = teacherService;
    }


    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(Long id){
        return classroomRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Classroom with id " + id + " does not exist"));
    }

    @Transactional
    public Classroom createClassroom(@Valid CreateClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setName_class(classroomDTO.getName_class());
        classroom.setTurn(classroomDTO.getTurn());
        classroom.setSchool_segment(classroomDTO.getSchool_segment());
        classroom.setId_teacher(teacherService.getTeacherById(classroomDTO.getId_teacher()));

        return classroomRepository.save(classroom);
    }

    @Transactional
    public Classroom updateClassroom(Long id, ClassroomDTO classroomDTO) {
        Optional<Classroom> classroom = classroomRepository.findById(id);

        if(classroom.isEmpty()) {
            throw new IllegalStateException("Classroom with id " + id + " does not exist");
        }

        classroom.get().setName_class(classroomDTO.getName_class());
        classroom.get().setId_teacher(teacherService.getTeacherById(classroomDTO.getIdTeacher()));
        classroom.get().setTurn(classroomDTO.getTurn());
        classroom.get().setSchool_segment(classroomDTO.getSchool_segment());

        return classroomRepository.save(classroom.get());
    }

    public void deleteClassroom(Long id) {
        Optional<Classroom> classroom = classroomRepository.findById(id);

        if(classroom.isEmpty()) {
            throw new IllegalStateException("Classroom with id " + id + " does not exist");
        }

        classroomRepository.delete(classroom.get());
    }
}
