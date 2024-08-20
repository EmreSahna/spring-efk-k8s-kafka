package me.emresahna.uniapp.service;

import me.emresahna.uniapp.dto.response.student.StudentResponse;
import me.emresahna.uniapp.dto.request.student.CreateStudentRequest;
import me.emresahna.uniapp.dto.request.student.UpdateStudentRequest;
import me.emresahna.uniapp.exception.exceptions.ResourceNotFoundException;
import me.emresahna.uniapp.mapper.StudentMapper;
import me.emresahna.uniapp.model.Student;
import me.emresahna.uniapp.repository.StudentRepository;
import me.emresahna.uniapp.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private KafkaTemplate<String, StudentResponse> kafkaTemplate;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    @DisplayName("Create Student")
    public void testCreateStudent() {
        // Create a mock request
        CreateStudentRequest request = new CreateStudentRequest();

        // Create a mock student
        Student student = new Student();
        when(studentMapper.mapRequestToStudent(request)).thenReturn(student);

        // Call the method under test
        studentService.createStudent(request);

        // Verify that the studentRepository's save method was called with the mock student
        verify(studentRepository).save(student);
    }

    @Test
    @DisplayName("Get All Students")
    public void testGetAllStudents() {
        // Create a mock list of students
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // Create a mock list of student responses
        List<StudentResponse> studentResponses = Arrays.asList(new StudentResponse(), new StudentResponse());
        when(studentMapper.mapStudentToResponse(any(Student.class))).thenReturn(studentResponses.get(0), studentResponses.get(1));

        // Call the method under test
        List<StudentResponse> result = studentService.getAllStudents();

        // Verify that the studentRepository's findAll method was called
        verify(studentRepository).findAll();

        // Verify that the kafkaTemplate's send method was called for each student response
        verify(kafkaTemplate, times(2)).send(eq("student-topic"), any(StudentResponse.class));

        // Verify the result
        assertEquals(studentResponses, result);
    }

    @Test
    @DisplayName("Get Student")
    public void testGetStudent() {
        // Create a mock student
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Create a mock student response
        StudentResponse studentResponse = new StudentResponse();
        when(studentMapper.mapStudentToResponse(student)).thenReturn(studentResponse);

        // Call the method under test
        StudentResponse result = studentService.getStudent(1L);

        // Verify that the studentRepository's findById method was called
        verify(studentRepository).findById(1L);

        // Verify the result
        assertEquals(studentResponse, result);
    }

    @Test
    @DisplayName("Update Student")
    public void testUpdateStudent() {
        // Create a mock student
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Create a mock request
        UpdateStudentRequest request = new UpdateStudentRequest();

        // Call the method under test
        studentService.updateStudent(1L, request);

        // Verify that the studentRepository's findById method was called
        verify(studentRepository).findById(1L);

        // Verify that the studentRepository's save method was called with the mock student
        verify(studentRepository).save(studentMapper.mapUpdateRequestToStudent(student, request));
    }

    @Test
    @DisplayName("Delete Student")
    public void testDeleteStudent() {
        // Create a mock student
        Student student = new Student();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Call the method under test
        studentService.deleteStudent(1L);

        // Verify that the studentRepository's findById method was called
        verify(studentRepository).findById(1L);

        // Verify that the studentRepository's deleteById method was called with the student's id
        verify(studentRepository).deleteById(student.getId());
    }

    @Test
    @DisplayName("Get Student - Throws StudentNotFoundException")
    public void testGetStudent_ThrowsStudentNotFoundException() {
        // Return an empty optional to simulate a student not found scenario
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the method under test, which should throw a StudentNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudent(1L);
        });
    }
}