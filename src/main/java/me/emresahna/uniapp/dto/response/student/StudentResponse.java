package me.emresahna.uniapp.dto.response.student;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
}
