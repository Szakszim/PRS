package server.controllers;


import dtos.PresenceOnLectureDto;
import entities.PresenceOnLecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.PresenceOnLectureRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PresenceOnLectureController {

    @Autowired
    private PresenceOnLectureRepository presenceOnLectureRepository;

    @Transactional
    @PostMapping(value = "/presenceonlecture")
    public ResponseEntity savePresenceOnLecture(@RequestBody @Valid PresenceOnLecture presenceOnLecture, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            presenceOnLectureRepository.save(presenceOnLecture);
            return ServerResponse.positive(presenceOnLecture);
        }
    }

    @GetMapping(value = "/presenceonlecture/{id}")
    public PresenceOnLectureDto getPresenceOnLecture(@PathVariable("id") Integer id) {
        return presenceOnLectureRepository.findPresenceOnLectureById(id);
    }

    @Transactional
    @DeleteMapping(value = "/presenceonlecture/{id}")
    public void deletePresenceOnLecture(@PathVariable("id") Integer id) {
        presenceOnLectureRepository.deletePresenceOnLectureById(id);
    }

    @GetMapping(value = "/presenceonlectures")
    public List<PresenceOnLectureDto> getPresenceOnLectures() {
        return presenceOnLectureRepository.findAllPresenceOnLecturesAsDto();
    }


}
