package server.controllers;


import dtos.StudentDto;
import entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.StudentRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @PostMapping(value = "/student")
    public ResponseEntity saveStudent(@RequestBody @Valid StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            studentRepository.save(studentDto.toEntity());
            return ServerResponse.positive(studentDto.toEntity());
        }
    }

    @GetMapping(value = "/student/{id}")
    public StudentDto getStudent(@PathVariable("id") Integer id) {
        return studentRepository.findStudentById(id);
    }

    @Transactional
    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable("id") Integer id) {
        studentRepository.deleteStudentById(id);
    }

    @GetMapping(value = "/students")
    public List<StudentDto> getStudents() {
        return studentRepository.findAllStudentsAsDto();
    }

    @GetMapping(value = "/student/{name}/{surname}")
    public Student findByFirstNameAndLastName(@PathVariable("name") String name, @PathVariable("surname") String surname){
        return studentRepository.findByFirstNameAndLastName(name, surname);
    }
}
