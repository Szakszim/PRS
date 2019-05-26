package server.controllers;


import dtos.LectureDto;
import entities.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.LectureRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LectureController {

    @Autowired
    private LectureRepository lectureRepository;

    @Transactional
    @PostMapping(value = "/lecture")
    public ResponseEntity saveLecture(@RequestBody @Valid Lecture lecture, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            lectureRepository.save(lecture);
            return ServerResponse.positive(lecture);
        }
    }

    @GetMapping(value = "/lecture/{id}")
    public LectureDto getLecture(@PathVariable("id") Integer id) {
        return lectureRepository.findLectureById(id);
    }

    @Transactional
    @DeleteMapping(value = "/lecture/{id}")
    public void deleteLecture(@PathVariable("id") Integer id) {
        lectureRepository.deleteLectureById(id);
    }

    @GetMapping(value = "/lectures")
    public List<LectureDto> getLectures() {
        return lectureRepository.findAllLecturesAsDto();
    }


}
