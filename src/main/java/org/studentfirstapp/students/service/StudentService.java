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
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("A student with this email already exists");
        }

        StudentEntity student = new StudentEntity();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());

        StudentEntity savedStudent = studentRepository.save(student);

        return mapToResponse(savedStudent);
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {

        StudentEntity student = findStudentOrThrow(id);

        return mapToResponse(student);
    }

    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {

        StudentEntity student = findStudentOrThrow(id);

        boolean emailUsedByAnotherStudent =
                studentRepository.existsByEmailAndIdNot(
                        request.email(),
                        id
                );

        if (emailUsedByAnotherStudent) {
            throw new DuplicateResourceException(
                    "Another student is already using this email"
            );
        }

        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setEmail(request.email());

        StudentEntity updatedStudent = studentRepository.save(student);

        return mapToResponse(updatedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {

        StudentEntity student = findStudentOrThrow(id);

        studentRepository.delete(student);
    }

    private StudentEntity findStudentOrThrow(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id
                ));
    }

    private StudentResponse mapToResponse(StudentEntity student) {

        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
