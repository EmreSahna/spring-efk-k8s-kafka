package me.emresahna.uniapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.dto.request.instructor.CreateInstructorRequest;
import me.emresahna.uniapp.exception.ResourceNotFoundException;
import me.emresahna.uniapp.mapper.InstructorMapper;
import me.emresahna.uniapp.model.Instructor;
import me.emresahna.uniapp.repository.InstructorRepository;
import me.emresahna.uniapp.service.InstructorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Override
    public void createInstructor(CreateInstructorRequest createInstructorRequest) {
        saveInstructor(instructorMapper.mapRequestToInstructor(createInstructorRequest));
    }

    @Override
    public void saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(this::throwInstructorNotFoundException);
    }

    public ResourceNotFoundException throwInstructorNotFoundException() {
        return new ResourceNotFoundException("INSTRUCTOR_NOT_FOUND");
    }
}
