package me.emresahna.uniapp.controller;

import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.dto.request.instructor.CreateInstructorRequest;
import me.emresahna.uniapp.service.InstructorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @PostMapping
    public void createInstructor(@RequestBody CreateInstructorRequest createInstructorRequest) {
        instructorService.createInstructor(createInstructorRequest);
    }
}
