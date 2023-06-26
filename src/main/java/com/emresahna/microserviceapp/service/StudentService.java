package com.emresahna.microserviceapp.service;

import com.emresahna.microserviceapp.dto.StudentResponse;
import com.emresahna.microserviceapp.dto.request.CreateStudentRequest;
import com.emresahna.microserviceapp.dto.request.UpdateStudentRequest;

import java.util.List;

public interface StudentService {
    void createStudent(CreateStudentRequest request);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudent(Long id);
    void updateStudent(Long id, UpdateStudentRequest request);
    void deleteStudent(Long id);
}
