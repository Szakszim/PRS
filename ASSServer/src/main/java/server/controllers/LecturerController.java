package server.controllers;


import dtos.LecturerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.LecturerRepository;

@RestController
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;

    @GetMapping(value = "/lecturer")
    public LecturerDto getLecturer(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        return lecturerRepository.findLecturerByEMailAndPassword(email, password);
    }


}
