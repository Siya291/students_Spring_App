package org.studentfirstapp.students.service;


import org.springframework.stereotype.Service;
import org.studentfirstapp.students.DTO.StudentRequest;
import org.studentfirstapp.students.DTO.StudentResponse;
import org.studentfirstapp.students.entity.StudentEntity;
import org.studentfirstapp.students.exceptions.DuplicateResourceException;
import org.studentfirstapp.students.exceptions.ResourceNotFoundException;
import org.studentfirstapp.students.repository.StudentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface StudentService {

    StudentResponse create(StudentRequest request);

    List<StudentResponse> findAll();

    StudentResponse findById(Long id);

    List<StudentResponse> findByDepartment(Long departmentId);
}
