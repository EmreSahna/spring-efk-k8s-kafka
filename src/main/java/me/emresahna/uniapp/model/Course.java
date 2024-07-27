package me.emresahna.uniapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String courseName;
    private int credit;
    @ManyToMany(mappedBy = "courses")
    private Set<Instructor> instructors;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
