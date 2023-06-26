package com.emresahna.microserviceapp.mapper;

import com.emresahna.microserviceapp.dto.StudentResponse;
import com.emresahna.microserviceapp.dto.request.CreateStudentRequest;
import com.emresahna.microserviceapp.dto.request.UpdateStudentRequest;
import com.emresahna.microserviceapp.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    Student mapRequestToStudent(CreateStudentRequest request);
    StudentResponse mapStudentToResponse(Student student);
    Student mapUpdateRequestToStudent(@MappingTarget Student student, UpdateStudentRequest request);
}
