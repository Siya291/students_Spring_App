package org.studentfirstapp.students.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.studentfirstapp.students.DTO.StudentRequest;
import org.studentfirstapp.students.DTO.StudentResponse;
import org.studentfirstapp.students.repository.StudentRepository;
import org.studentfirstapp.students.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAll() {

        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<StudentResponse>> findByDepartment(@PathVariable Long departmentId) {

        return ResponseEntity.ok(
                studentService.findByDepartment(departmentId)
        );
    }
}
