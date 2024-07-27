package me.emresahna.uniapp.repository;

import me.emresahna.uniapp.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveStudentToDBAndReturnSavedStudent() {
        //Arrange
        Student student = Student.builder()
                .name("Josh")
                .surname("Doe")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        //Act
        Student savedStudent = studentRepository.save(student);

        //Assert
        assertNotNull(savedStudent);
        assertEquals(student.getName(), savedStudent.getName());
    }

    @Test
    void saveStudentsToDBAndReturnAllStudents() {
        //Arrange
        Student student1 = Student.builder()
                .name("Josh")
                .surname("Doe")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        Student student2 = Student.builder()
                .name("Jane")
                .surname("Lee")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        //Act
        studentRepository.save(student1);
        studentRepository.save(student2);

        //Assert
        assertEquals(2, studentRepository.findAll().size());
    }

    @Test
    void saveStudentToDBAndGetById() {
        //Arrange
        Student student = Student.builder()
                .name("Xavier")
                .surname("Parker")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        //Act
        studentRepository.save(student);
        Student savedStudent = studentRepository.findById(student.getId()).get();

        //Assert
        assertNotNull(savedStudent);
        assertEquals(student.getName(), savedStudent.getName());
    }

    @Test
    void saveStudentToDBAndUpdateStudent() {
        //Arrange
        Student student = Student.builder()
                .name("Liam")
                .surname("Smith")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        //Act
        studentRepository.save(student);

        Student savedStudent = studentRepository.findById(student.getId()).get();
        savedStudent.setName("John");
        savedStudent.setSurname("Doe");
        savedStudent.setUsername("jdoe");
        savedStudent.setPassword("1234");
        savedStudent.setEmail("jdoe@gmail.com");
        Student updatedStudent = studentRepository.save(savedStudent);

        //Assert
        assertNotNull(updatedStudent);
        assertEquals("John", updatedStudent.getName());
        assertEquals("Doe", updatedStudent.getSurname());
        assertEquals("jdoe", updatedStudent.getUsername());
        assertEquals("1234", updatedStudent.getPassword());
        assertEquals("jdoe@gmail.com", updatedStudent.getEmail());
    }

    @Test
    void saveStudentToDBAndDeleteStudentThenReturnEmpty(){
        //Arrange
        Student student = Student.builder()
                .name("Liam")
                .surname("Smith")
                .username("jdoe")
                .password("1234")
                .email("jdoe@gmail.com").build();

        //Act
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
        Optional<Student> deletedStudent = studentRepository.findById(student.getId());

        //Assert
        assertEquals(0, studentRepository.findAll().size());
        assertEquals(deletedStudent, Optional.empty());
    }
}
