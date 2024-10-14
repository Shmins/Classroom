<<<<<<< Updated upstream
package com.classroom.gerenciamento.Repository;

import com.classroom.gerenciamento.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
=======
package com.classroom.main.repository;

import com.classroom.main.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> Stashed changes
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
