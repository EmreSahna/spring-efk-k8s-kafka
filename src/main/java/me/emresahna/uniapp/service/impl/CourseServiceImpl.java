package me.emresahna.uniapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.emresahna.uniapp.dto.request.course.CreateCourseRequest;
import me.emresahna.uniapp.dto.response.course.CourseResponse;
import me.emresahna.uniapp.exception.ExceptionType;
import me.emresahna.uniapp.exception.exceptions.ResourceNotFoundException;
import me.emresahna.uniapp.mapper.CourseMapper;
import me.emresahna.uniapp.model.Course;
import me.emresahna.uniapp.repository.CourseRepository;
import me.emresahna.uniapp.service.CourseService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public void createCourse(CreateCourseRequest createCourseRequest) {
        saveCourse(courseMapper.mapCreateCourseRequestToCourse(createCourseRequest));
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public CourseResponse getCourse(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::mapCourseToCourseResponse)
                .orElseThrow(this::throwCourseNotFoundException);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(this::throwCourseNotFoundException);
    }

    public ResourceNotFoundException throwCourseNotFoundException() {
        return new ResourceNotFoundException(ExceptionType.COURSE_NOT_FOUND);
    }
}
