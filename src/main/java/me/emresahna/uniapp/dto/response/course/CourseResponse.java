package me.emresahna.uniapp.dto.response.course;

import lombok.Data;
import me.emresahna.uniapp.dto.response.instructor.InstructorResponse;
import me.emresahna.uniapp.dto.response.student.StudentResponse;

import java.util.List;

@Data
public class CourseResponse {
    private Long id;
    private String courseCode;
    private String courseName;
    private int credit;
    private List<StudentResponse> students;
    private List<InstructorResponse> instructors;
}
