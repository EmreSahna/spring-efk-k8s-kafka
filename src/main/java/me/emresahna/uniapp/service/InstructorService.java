package me.emresahna.uniapp.service;

import me.emresahna.uniapp.dto.request.instructor.CreateInstructorRequest;
import me.emresahna.uniapp.model.Instructor;

public interface InstructorService {
    void createInstructor(CreateInstructorRequest createInstructorRequest);
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(Long id);
}
