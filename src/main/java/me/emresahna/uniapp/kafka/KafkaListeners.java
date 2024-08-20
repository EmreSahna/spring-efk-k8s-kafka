package me.emresahna.uniapp.kafka;

import me.emresahna.uniapp.dto.response.course.CourseResponse;
import me.emresahna.uniapp.dto.response.student.StudentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {
    @KafkaListener(topics = "student-get-topic", groupId = "student-group")
    void logStudentGetAll(StudentResponse response) {
        log.info("Listener received data : {}, ", response);
    }

    @KafkaListener(topics = "student-assign-course-topic", groupId = "student-group")
    void logStudentAssignToCourse(CourseResponse courseResponse) {
        log.info("Student assigned to course : {}", courseResponse);
    }
}