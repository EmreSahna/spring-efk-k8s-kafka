package com.emresahna.microserviceapp.controller;

import com.emresahna.microserviceapp.dto.StudentResponse;
import com.emresahna.microserviceapp.dto.request.CreateStudentRequest;
import com.emresahna.microserviceapp.dto.request.UpdateStudentRequest;
import com.emresahna.microserviceapp.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public void createStudent(@RequestBody @Valid CreateStudentRequest request) {
        studentService.createStudent(request);
    }

    @CircuitBreaker(name = "getAllStudents")
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest request) {
        studentService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
