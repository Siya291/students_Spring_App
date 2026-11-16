package org.studentfirstapp.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.studentfirstapp.students.entity.StudentEntity;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<StudentEntity> findByEmail(String email);
}
