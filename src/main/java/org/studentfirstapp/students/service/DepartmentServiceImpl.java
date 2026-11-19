package org.studentfirstapp.students.service;

import org.springframework.stereotype.Service;
import org.studentfirstapp.students.DTO.DepartmentRequest;
import org.studentfirstapp.students.DTO.DepartmentResponse;
import org.studentfirstapp.students.entity.DepartmentEntity;
import org.studentfirstapp.students.exceptions.ResourceNotFoundException;
import org.studentfirstapp.students.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(request.name());
        department.setDescription(request.description());

        DepartmentEntity saved = repository.save(department);

        return mapToResponse(saved);
    }

    @Override
    public List<DepartmentResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DepartmentResponse findById(Long id) {

        DepartmentEntity department = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found: " + id
                        ));

        return mapToResponse(department);
    }

    private DepartmentResponse mapToResponse(
            DepartmentEntity department) {

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}
