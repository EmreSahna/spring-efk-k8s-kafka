package me.emresahna.uniapp.repository;

import me.emresahna.uniapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByUsername(String username);
}
