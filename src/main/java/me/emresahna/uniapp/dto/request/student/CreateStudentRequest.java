package me.emresahna.uniapp.dto.request.student;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateStudentRequest {
    @NotBlank(message = "exception.emptyName")
    private String name;
    @NotBlank(message = "exception.emptySurname")
    private String surname;
    private String username;
    private String password;
    private String email;
}
