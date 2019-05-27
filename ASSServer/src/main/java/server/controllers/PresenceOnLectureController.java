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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PresenceOnLectureController {

    @Autowired
    private PresenceOnLectureRepository presenceOnLectureRepository;

    @Transactional
    @PostMapping(value = "/presenceOnLecture")
    public ResponseEntity savePresenceOnLecture(@RequestBody @Valid PresenceOnLectureDto presenceOnLectureDto, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            presenceOnLectureRepository.save(presenceOnLectureDto.toEntity());
            return ServerResponse.positive(presenceOnLectureDto.toEntity());
        }
    }

    @GetMapping(value = "/presenceOnLecture/{id}")
    public PresenceOnLectureDto getPresenceOnLecture(@PathVariable("id") Integer id) {
        return presenceOnLectureRepository.findPresenceOnLectureById(id);
    }

    @Transactional
    @DeleteMapping(value = "/presenceOnLecture/{id}")
    public void deletePresenceOnLecture(@PathVariable("id") Integer id) {
        presenceOnLectureRepository.deletePresenceOnLectureById(id);
    }

    @GetMapping(value = "/presenceOnLectures")
    public List<PresenceOnLectureDto> getPresenceOnLectures() {
        return presenceOnLectureRepository.findAllPresenceOnLecturesAsDto();
    }


    @GetMapping(value = "/presenceOnLectures/{date}/{hour}/{room}")
    public List<PresenceOnLecture> findAllByPresenceDateAndHourTimeAndRoom(@PathVariable("date") String date, @PathVariable("hour") String hour, @PathVariable("room") String room) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateSql = formatter.parse(date);
        return presenceOnLectureRepository.findAllByPresenceDateAndHourTimeAndRoom(dateSql, hour, room);
    }

}
