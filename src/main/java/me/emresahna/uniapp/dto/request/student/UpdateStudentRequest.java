package me.emresahna.uniapp.dto.request.student;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}
