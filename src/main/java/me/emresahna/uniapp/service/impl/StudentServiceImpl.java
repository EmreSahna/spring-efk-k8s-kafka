package me.emresahna.uniapp.service.impl;

import me.emresahna.uniapp.dto.response.student.StudentResponse;
import me.emresahna.uniapp.dto.request.student.CreateStudentRequest;
import me.emresahna.uniapp.dto.request.student.UpdateStudentRequest;
import me.emresahna.uniapp.exception.ExceptionType;
import me.emresahna.uniapp.exception.exceptions.AlreadyExistsException;
import me.emresahna.uniapp.exception.exceptions.ResourceNotFoundException;
import me.emresahna.uniapp.mapper.StudentMapper;
import me.emresahna.uniapp.model.Student;
import me.emresahna.uniapp.repository.StudentRepository;
import me.emresahna.uniapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void createStudent(CreateStudentRequest request){
        Optional<Student> foundStudent = studentRepository.findStudentByUsername(request.getUsername());

        if(foundStudent.isPresent()) {
            throw studentAlreadyExistsException();
        }

        saveStudent(studentMapper.mapRequestToStudent(request));
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<StudentResponse> getAllStudents(){
        var students = studentRepository.findAll()
                .stream()
                .map(studentMapper::mapStudentToResponse)
                .collect(Collectors.toList());

        students.forEach(studentResponse -> kafkaTemplate.send("student-get-topic", studentResponse));

        return students;
    }

    @Override
    public StudentResponse getStudent(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::mapStudentToResponse)
                .orElseThrow(this::studentNotFoundException);
    }

    @Override
    public void updateStudent(Long id, UpdateStudentRequest request) {
        var student = studentRepository.findById(id)
                .orElseThrow(this::studentNotFoundException);
        studentRepository.save(studentMapper.mapUpdateRequestToStudent(student, request));
    }

    @Override
    public void deleteStudent(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(this::studentNotFoundException);
        studentRepository.deleteById(student.getId());
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(this::studentNotFoundException);
    }

    private ResourceNotFoundException studentNotFoundException() {
        return new ResourceNotFoundException(ExceptionType.STUDENT_NOT_FOUND);
    }

    private AlreadyExistsException studentAlreadyExistsException() {
        return new AlreadyExistsException(ExceptionType.STUDENT_ALREADY_EXISTS);
    }
}
