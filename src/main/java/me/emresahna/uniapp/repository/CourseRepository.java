package me.emresahna.uniapp.repository;

import me.emresahna.uniapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
