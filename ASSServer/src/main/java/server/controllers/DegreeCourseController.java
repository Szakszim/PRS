package server.controllers;


import dtos.DegreeCourseDto;
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
    @PostMapping(value = "/degreeCourse")
    public ResponseEntity saveDegreeCourse(@RequestBody @Valid DegreeCourseDto degreeCourseDto, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            degreeCourseRepository.save(degreeCourseDto.toEntity());
            return ServerResponse.positive(degreeCourseDto.toEntity());
        }
    }

    @GetMapping(value = "/degreeCourse/{id}")
    public DegreeCourseDto getDegreeCourse(@PathVariable("id") Integer id) {
        return degreeCourseRepository.findDegreeCourseById(id);
    }

    @Transactional
    @DeleteMapping(value = "/degreeCourse/{id}")
    public void deleteDegreeCourse(@PathVariable("id") Integer id) {
        degreeCourseRepository.deleteDegreeCourseById(id);
    }

    @GetMapping(value = "/degreeCourses")
    public List<DegreeCourseDto> getDegreeCourses() {
        return degreeCourseRepository.findAllDegreeCoursesAsDto();
    }


}
