package org.studentfirstapp.students.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentfirstapp.students.DTO.StudentRequest;
import org.studentfirstapp.students.DTO.StudentResponse;
import org.studentfirstapp.students.entity.DepartmentEntity;
import org.studentfirstapp.students.entity.StudentEntity;
import org.studentfirstapp.students.exceptions.ResourceNotFoundException;
import org.studentfirstapp.students.repository.DepartmentRepository;
import org.studentfirstapp.students.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {

        DepartmentEntity department = departmentRepository
                .findById(request.departmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found: "
                                        + request.departmentId()
                        ));

        StudentEntity student = new StudentEntity();

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());
        student.setDepartment(department);

        StudentEntity saved = studentRepository.save(student);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse findById(Long id) {

        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found: " + id
                        ));

        return mapToResponse(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> findByDepartment(
            Long departmentId) {

        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException(
                    "Department not found: " + departmentId
            );
        }

        return studentRepository
                .findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private StudentResponse mapToResponse(StudentEntity student) {

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDepartment().getId(),
                student.getDepartment().getName()
        );
    }
}
