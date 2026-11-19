package org.studentfirstapp.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.studentfirstapp.students.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
