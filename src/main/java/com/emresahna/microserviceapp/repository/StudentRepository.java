package com.emresahna.microserviceapp.repository;

import com.emresahna.microserviceapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
