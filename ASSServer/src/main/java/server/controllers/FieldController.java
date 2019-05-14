package server.controllers;


import dtos.other.BasicFieldDataDto;
import entities.Field;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.FieldRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class FieldController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private FieldRepository fieldRepository;

    @Transactional
    @RequestMapping(value = "/savefield", method = RequestMethod.POST)
    public ResponseEntity saveField(@RequestBody @Valid Field field, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();

        field = em.merge(field);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(field, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/basicfielddatabyfieldid", method = RequestMethod.POST)
    public ResponseEntity getBasicFieldDataByFieldId(@RequestBody @Valid Long fieldId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        BasicFieldDataDto basicFieldDataDtoResult = fieldRepository.getBasicFieldDataByFieldId(fieldId);

        return new ResponseEntity(basicFieldDataDtoResult, new HttpHeaders(), HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/fieldlist", method = RequestMethod.POST)
//    public ResponseEntity findAllFieldsWithOwnerData() {
//        List<FieldRowModelDto> fieldListResult = fieldRepository.findAllFieldsWithOwnerData();
//        return new ResponseEntity(fieldListResult, new HttpHeaders(), HttpStatus.OK);
//    }

}
