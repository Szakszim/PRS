package server.controllers;


import dtos.LectureTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.LectureTypeRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LectureTypeController {


    @Autowired
    private LectureTypeRepository lectureTypeRepository;

    @Transactional
    @PostMapping(value = "/lectureType")
    public ResponseEntity saveLectureType(@RequestBody @Valid LectureTypeDto lectureTypeDto, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            lectureTypeRepository.save(lectureTypeDto.toEntity());
            return ServerResponse.positive(lectureTypeDto.toEntity());
        }
    }

    @GetMapping(value = "/lectureType/{id}")
    public LectureTypeDto getLectureType(@PathVariable("id") Integer id) {
        return lectureTypeRepository.findLectureTypeById(id);
    }

    @Transactional
    @DeleteMapping(value = "/lectureType/{id}")
    public void deleteLectureType(@PathVariable("id") Integer id) {
        lectureTypeRepository.deleteLectureTypeById(id);
    }

    @GetMapping(value = "/lectureTypes")
    public List<LectureTypeDto> getLectureTypes() {
        return lectureTypeRepository.findAllLectureTypesAsDto();
    }


}
