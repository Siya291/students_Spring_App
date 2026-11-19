package org.studentfirstapp.students.service;

import org.studentfirstapp.students.DTO.DepartmentRequest;
import org.studentfirstapp.students.DTO.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> findAll();

    DepartmentResponse findById(Long id);
}
