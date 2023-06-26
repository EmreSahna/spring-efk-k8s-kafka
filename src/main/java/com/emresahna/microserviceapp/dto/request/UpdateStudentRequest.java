package com.emresahna.microserviceapp.dto.request;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private String name;
    private String surname;
    private String groupName;
}
