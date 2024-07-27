package me.emresahna.uniapp.mapper;

import me.emresahna.uniapp.dto.request.instructor.CreateInstructorRequest;
import me.emresahna.uniapp.model.Instructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    Instructor mapRequestToInstructor(CreateInstructorRequest createInstructorRequest);
}
