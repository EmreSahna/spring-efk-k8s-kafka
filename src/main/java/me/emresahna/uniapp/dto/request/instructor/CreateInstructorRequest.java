package me.emresahna.uniapp.dto.request.instructor;

import lombok.Data;

@Data
public class CreateInstructorRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}
