package org.studentfirstapp.students.DTO;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
