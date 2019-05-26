package server.controllers;


import dtos.DegreeCourseDto;
import entities.DegreeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.DegreeCourseRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DegreeCourseController {

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;

    @Transactional
    @PostMapping(value = "/degreecourse")
    public ResponseEntity saveDegreeCourse(@RequestBody @Valid DegreeCourse degreeCourse, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            degreeCourseRepository.save(degreeCourse);
            return ServerResponse.positive(degreeCourse);
        }
    }

    @GetMapping(value = "/degreecourse/{id}")
    public DegreeCourseDto getDegreeCourse(@PathVariable("id") Integer id) {
        return degreeCourseRepository.findDegreeCourseById(id);
    }

    @Transactional
    @DeleteMapping(value = "/degreecourse/{id}")
    public void deleteDegreeCourse(@PathVariable("id") Integer id) {
        degreeCourseRepository.deleteDegreeCourseById(id);
    }

    @GetMapping(value = "/degreecourses")
    public List<DegreeCourseDto> getDegreeCourses() {
        return degreeCourseRepository.findAllDegreeCoursesAsDto();
    }


}
