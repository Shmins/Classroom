<<<<<<< Updated upstream
package com.classroom.gerenciamento.Service;



import com.classroom.gerenciamento.Model.Teacher;
import com.classroom.gerenciamento.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.classroom.gerenciamento.Model.DTO.TeacherDto;
=======
package com.classroom.main.service;

import com.classroom.main.controller.dto.CreateTeacherDTO;
import com.classroom.main.controller.dto.TeacherDTO;
import com.classroom.main.model.Teacher;
import com.classroom.main.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
>>>>>>> Stashed changes

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
<<<<<<< Updated upstream
    private final TeacherRepository teacherRepository;
    private TeacherDto TeacherDto;
=======

    private final TeacherRepository teacherRepository;
>>>>>>> Stashed changes

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
<<<<<<< Updated upstream

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
=======
        if(teacher.isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        return teacherRepository.save(teacher.get());
    }

    public Teacher createTeacher(@Valid CreateTeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setPassword(teacherDTO.getPassword());
>>>>>>> Stashed changes

        return teacherRepository.save(teacher);
    }

<<<<<<< Updated upstream
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


=======
    public Teacher updateTeacher(Long id, TeacherDTO teacherDTO) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        teacher.get().setName(teacherDTO.getName());
        teacher.get().setEmail(teacherDTO.getEmail());
        teacher.get().setPassword(teacherDTO.getPassword());
        return teacherRepository.save(teacher.get());
    }

    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty()) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.delete(teacher.get());
    }
>>>>>>> Stashed changes
}
