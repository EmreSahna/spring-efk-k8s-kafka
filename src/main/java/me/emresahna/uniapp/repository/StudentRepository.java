package me.emresahna.uniapp.repository;

import me.emresahna.uniapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
