package me.emresahna.uniapp.service;

public interface CourseAssignmentService {
    void assignInstructorToCourse(Long courseId, Long instructorId);
    void assignStudentToCourse(Long courseId, Long studentId);
}
