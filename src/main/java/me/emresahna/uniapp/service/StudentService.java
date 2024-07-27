package me.emresahna.uniapp.service;

import me.emresahna.uniapp.dto.response.student.StudentResponse;
import me.emresahna.uniapp.dto.request.student.CreateStudentRequest;
import me.emresahna.uniapp.dto.request.student.UpdateStudentRequest;
import me.emresahna.uniapp.model.Student;

import java.util.List;

public interface StudentService {
    void createStudent(CreateStudentRequest request);
    void saveStudent(Student student);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudent(Long id);
    void updateStudent(Long id, UpdateStudentRequest request);
    void deleteStudent(Long id);
    Student findStudentById(Long id);
}
