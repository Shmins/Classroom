<<<<<<< Updated upstream
package com.classroom.gerenciamento.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
=======
package com.classroom.main.model;

import jakarta.persistence.*;
>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< Updated upstream
=======
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
>>>>>>> Stashed changes

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {
<<<<<<< Updated upstream
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDateTime created_at;

    @NotNull
    private String TeacherCol;
=======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    private String email;

    private String password;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
>>>>>>> Stashed changes
}
