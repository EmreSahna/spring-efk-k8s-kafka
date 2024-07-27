package me.emresahna.uniapp.service;

import me.emresahna.uniapp.dto.response.course.CourseResponse;
import me.emresahna.uniapp.model.Course;
import me.emresahna.uniapp.dto.request.course.CreateCourseRequest;

public interface CourseService {
    void createCourse(CreateCourseRequest createCourseRequest);
    void saveCourse(Course course);
    CourseResponse getCourse(Long id);
    Course findCourseById(Long id);
}
