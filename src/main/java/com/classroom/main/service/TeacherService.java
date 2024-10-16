package com.classroom.main.service;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDto;
import com.classroom.main.model.Teacher;
import com.classroom.main.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private TeacherDto TeacherDTO;



    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Teacher with id " + id + " does not exist"));
    }
    public Teacher createTeacher(@Valid CreateTeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setPassword(teacherDTO.getPassword());


        return teacherRepository.save(teacher);
    }


public Teacher updateTeacher(Long id, TeacherDto teacherDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(teacher.isEmpty()) {
            throw new IllegalStateException("Teacher with id " + id + " does not exist");
        }

        teacher.get().setName(teacherDTO.getName());
        teacher.get().setEmail(teacherDTO.getEmail());
        teacher.get().setPassword(teacherDTO.getPassword());

        return teacherRepository.save(teacher.get());
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
