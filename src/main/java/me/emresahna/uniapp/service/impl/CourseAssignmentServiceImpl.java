package me.emresahna.uniapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.model.Course;
import me.emresahna.uniapp.model.Instructor;
import me.emresahna.uniapp.model.Student;
import me.emresahna.uniapp.service.CourseAssignmentService;
import me.emresahna.uniapp.service.CourseService;
import me.emresahna.uniapp.service.InstructorService;
import me.emresahna.uniapp.service.StudentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseAssignmentServiceImpl implements CourseAssignmentService {
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        Instructor instructor = instructorService.findInstructorById(instructorId);
        Course course = courseService.findCourseById(courseId);

        course.getInstructors().add(instructor);
        instructor.getCourses().add(course);

        courseService.saveCourse(course);
        instructorService.saveInstructor(instructor);
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        course.getStudents().add(student);
        student.getCourses().add(course);

        courseService.saveCourse(course);
        studentService.saveStudent(student);
    }
}
