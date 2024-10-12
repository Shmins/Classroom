package com.classroom.gerenciamento.Service;



import com.classroom.gerenciamento.Model.Teacher;
import com.classroom.gerenciamento.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.classroom.gerenciamento.Model.DTO.TeacherDto;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private TeacherDto TeacherDto;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(teacher.isEmpty()) {
            throw new IllegalStateException("Teacher with id " + id + " does not exist");
        }

        teacher.get().setName(TeacherDto.getName());
        teacher.get().setEmail(TeacherDto.getEmail());
        teacher.get().setPassword(TeacherDto.getPassword());

        return teacherRepository.save(teacher.get());
    }

    public Teacher createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPassword(teacherDto.getPassword());

        return teacherRepository.save(teacher);
    }

public Teacher updateTeacher(Long id, TeacherDto teacherDto) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(teacher.isEmpty()) {
            throw new IllegalStateException("Teacher with id " + id + " does not exist");
        }

        teacher.get().setName(teacherDto.getName());
        teacher.get().setEmail(teacherDto.getEmail());
        teacher.get().setPassword(teacherDto.getPassword());

        return teacherRepository.save(teacher.get());
    }

    public void DeleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }


}
