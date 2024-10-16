package com.classroom.main.service;

import com.classroom.main.controller.dto.ClassroomDTO;
import com.classroom.main.controller.dto.CreateClassroomDTO;
import com.classroom.main.model.Classroom;
import com.classroom.main.repository.ClassroomRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private ClassroomDTO ClassroomDTO;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }


    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroomById(Long id){
        Optional<Classroom> classroom = classroomRepository.findById(id);

        if (classroom.isEmpty()) {
            throw new IllegalStateException("Classroom with id " + id + " does not exist");
        }

        classroom.get().setName_class(ClassroomDTO.getName_class());
        classroom.get().setId_teacher(ClassroomDTO.getId_teacher());
        classroom.get().setTurn(ClassroomDTO.getTurn());
        classroom.get().setSchool_segment(ClassroomDTO.getSchool_segment());

        return classroomRepository.save(classroom.get());
    }

    public Classroom createClassroom(@Valid CreateClassroomDTO classroomDTO) {
        Classroom classroom = new Classroom();
        classroom.setName_class(classroomDTO.getName_class());
        classroom.setTurn(classroomDTO.getTurn());
        classroom.setSchool_segment(classroomDTO.getSchool_segment());

        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Long id, ClassroomDTO classroomDTO) {
        Optional<Classroom> classroom = classroomRepository.findById(id);

        if(classroom.isEmpty()) {
            throw new IllegalStateException("Classroom with id " + id + " does not exist");
        }

        classroom.get().setName_class(classroomDTO.getName_class());
        classroom.get().setId_teacher(classroomDTO.getId_teacher());
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
