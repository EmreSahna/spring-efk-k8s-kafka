package com.emresahna.microserviceapp.service.impl;

import com.emresahna.microserviceapp.dto.StudentResponse;
import com.emresahna.microserviceapp.dto.request.CreateStudentRequest;
import com.emresahna.microserviceapp.dto.request.UpdateStudentRequest;
import com.emresahna.microserviceapp.exception.StudentNotFoundException;
import com.emresahna.microserviceapp.mapper.StudentMapper;
import com.emresahna.microserviceapp.repository.StudentRepository;
import com.emresahna.microserviceapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final KafkaTemplate<String, StudentResponse> kafkaTemplate;

    @Override
    public void createStudent(CreateStudentRequest request){
        studentRepository.save(studentMapper.mapRequestToStudent(request));
    }

    @Override
    public List<StudentResponse> getAllStudents(){
        var students = studentRepository.findAll()
                .stream()
                .map(studentMapper::mapStudentToResponse)
                .collect(Collectors.toList());

        students.forEach(studentResponse -> kafkaTemplate.send("student-topic", studentResponse));

        return students;
    }

    @Override
    public StudentResponse getStudent(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::mapStudentToResponse)
                .orElseThrow(this::throwStudentNotFoundException);
    }

    @Override
    public void updateStudent(Long id, UpdateStudentRequest request) {
        var student = studentRepository.findById(id)
                .orElseThrow(this::throwStudentNotFoundException);
        studentRepository.save(studentMapper.mapUpdateRequestToStudent(student, request));
    }

    @Override
    public void deleteStudent(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(this::throwStudentNotFoundException);
        studentRepository.deleteById(student.getId());
    }

    public StudentNotFoundException throwStudentNotFoundException() {
        return new StudentNotFoundException();
    }
}
