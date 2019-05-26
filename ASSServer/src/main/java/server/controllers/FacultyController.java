package server.controllers;


import dtos.FacultyDto;
import entities.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.FacultyRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    @PostMapping(value = "/faculty")
    public ResponseEntity saveFaculty(@RequestBody @Valid Faculty faculty, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            facultyRepository.save(faculty);
            return ServerResponse.positive(faculty);
        }
    }

    @GetMapping(value = "/faculty/{id}")
    public FacultyDto getFaculty(@PathVariable("id") Integer id) {
        return facultyRepository.findFacultyById(id);
    }

    @Transactional
    @DeleteMapping(value = "/faculty/{id}")
    public void deleteFaculty(@PathVariable("id") Integer id) {
        facultyRepository.deleteFacultyById(id);
    }

    @GetMapping(value = "/faculties")
    public List<FacultyDto> getFaculties() {
        return facultyRepository.findAllFacultiesAsDto();
    }


}
