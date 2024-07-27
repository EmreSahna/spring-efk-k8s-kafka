package me.emresahna.uniapp.repository;

import me.emresahna.uniapp.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
