package me.emresahna.uniapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.dto.request.course.CreateCourseRequest;
import me.emresahna.uniapp.dto.response.course.CourseResponse;
import me.emresahna.uniapp.service.CourseAssignmentService;
import me.emresahna.uniapp.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseAssignmentService courseAssignmentService;

    @PostMapping
    public void createCourse(@RequestBody @Valid CreateCourseRequest createCourseRequest) {
        courseService.createCourse(createCourseRequest);
    }

    @PutMapping("/{courseId}/assign-instructor/{instructorId}")
    public void addInstructorToCourse(@PathVariable Long courseId, @PathVariable Long instructorId) {
        courseAssignmentService.assignInstructorToCourse(courseId, instructorId);
    }

    @PutMapping("/{courseId}/assign-student/{studentId}")
    public void addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseAssignmentService.assignStudentToCourse(courseId, studentId);
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourse(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }
}
