package org.studentfirstapp.students.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String description
) {
}
