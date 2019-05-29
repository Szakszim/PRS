package server.repositories;

import dtos.StudentDto;
import entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    StudentDto findStudentById(Integer id);

    void deleteStudentById(Integer id);

    @Query("SELECT NEW dtos.StudentDto(s) FROM Student s")
    List<StudentDto> findAllStudentsAsDto();

    Student findByFirstNameAndLastName(String name, String surname);
}
