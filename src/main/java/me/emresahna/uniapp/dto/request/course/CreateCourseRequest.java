package me.emresahna.uniapp.dto.request.course;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String courseCode;
    private String courseName;
    private int credit;
}
