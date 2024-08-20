package me.emresahna.uniapp.dto.request.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateStudentRequest {
    @NotBlank(message = "exception.emptyName")
    private String name;
    @NotBlank(message = "exception.emptySurname")
    private String surname;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String email;
}
