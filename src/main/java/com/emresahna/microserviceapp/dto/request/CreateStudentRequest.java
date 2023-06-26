package com.emresahna.microserviceapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotBlank(message = "exception.emptyName")
    private String name;

    @NotBlank(message = "exception.emptySurname")
    private String surname;

    @NotBlank(message = "exception.emptyGroupName")
    private String groupName;
}
