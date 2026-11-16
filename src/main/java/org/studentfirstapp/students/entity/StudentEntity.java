package org.studentfirstapp.students.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", nullable = false, length = 25)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 25)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;

}
