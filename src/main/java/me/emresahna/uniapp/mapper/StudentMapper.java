package me.emresahna.uniapp.mapper;

import me.emresahna.uniapp.dto.response.student.StudentResponse;
import me.emresahna.uniapp.dto.request.student.CreateStudentRequest;
import me.emresahna.uniapp.dto.request.student.UpdateStudentRequest;
import me.emresahna.uniapp.model.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    Student mapRequestToStudent(CreateStudentRequest request);
    StudentResponse mapStudentToResponse(Student student);
    Student mapUpdateRequestToStudent(@MappingTarget Student student, UpdateStudentRequest request);
}
