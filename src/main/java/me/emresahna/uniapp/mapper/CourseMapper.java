package me.emresahna.uniapp.mapper;

import me.emresahna.uniapp.dto.request.course.CreateCourseRequest;
import me.emresahna.uniapp.dto.response.course.CourseResponse;
import me.emresahna.uniapp.dto.response.instructor.InstructorResponse;
import me.emresahna.uniapp.dto.response.student.StudentResponse;
import me.emresahna.uniapp.model.Course;
import me.emresahna.uniapp.model.Instructor;
import me.emresahna.uniapp.model.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course mapCreateCourseRequestToCourse(CreateCourseRequest createCourseRequest);
    CourseResponse mapCourseToCourseResponse(Course course);
    List<StudentResponse> mapStudentsToStudentResponses(List<Student> students);
    List<InstructorResponse> mapInstructorsToInstructorResponses(List<Instructor> instructors);
}
