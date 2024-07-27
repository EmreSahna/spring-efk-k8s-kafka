package me.emresahna.uniapp.dto.response.instructor;

import lombok.Data;

@Data
public class InstructorResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}
