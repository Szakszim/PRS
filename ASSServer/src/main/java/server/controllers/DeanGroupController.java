package server.controllers;


import dtos.DeanGroupDto;
import entities.DeanGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.DeanGroupRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DeanGroupController {

    @Autowired
    private DeanGroupRepository deanGroupRepository;

    @Transactional
    @PostMapping(value = "/deangroup")
    public ResponseEntity saveDeanGroup(@RequestBody @Valid DeanGroup deanGroup, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            deanGroupRepository.save(deanGroup);
            return ServerResponse.positive(deanGroup);
        }
    }

    @GetMapping(value = "/deangroup/{id}")
    public DeanGroupDto getDeanGroup(@PathVariable("id") Integer id) {
        return deanGroupRepository.findDeanGroupById(id);
    }

    @Transactional
    @DeleteMapping(value = "/deangroup/{id}")
    public void deleteDeanGroup(@PathVariable("id") Integer id) {
        deanGroupRepository.deleteDeanGroupById(id);
    }

    @GetMapping(value = "/deangroups")
    public List<DeanGroupDto> getDeanGroups() {
        return deanGroupRepository.findAllDeanGroupAsDto();
    }


}
