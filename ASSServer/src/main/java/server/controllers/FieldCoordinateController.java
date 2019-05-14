package server.controllers;


import entities.Field;
import entities.FieldCoordinate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.FieldCoordinateRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class FieldCoordinateController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private FieldCoordinateRepository fieldCoordinateRepository;

    @Transactional
    @RequestMapping(value = "/savefieldcoordinate", method = RequestMethod.POST)
    public ResponseEntity saveField(@RequestBody @Valid List<FieldCoordinate> list, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();

        FieldCoordinate fieldCoordinate = new FieldCoordinate();
        for (int i = 0; i < list.size(); i++) {
            fieldCoordinate = em.merge(list.get(i));
            list.set(i,fieldCoordinate);
        }
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/fieldcoordinatesbyfarmid", method = RequestMethod.POST)
    public ResponseEntity findAllCoordinatesByFarmId(@RequestBody @Valid Long farmId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<List<FieldCoordinate>> resultList = fieldCoordinateRepository.findAllCoordinatesByFarmId(farmId);

        return new ResponseEntity(resultList, new HttpHeaders(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/fieldbyaccount", method = RequestMethod.POST)
//    public ResponseEntity getFieldDataByAccountId(@RequestBody @Valid Long accountId, BindingResult result) {
//        if (result.hasErrors()) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//
//        Field fieldResult = fieldRepository.findFieldByAccountId(accountId);
//
////        AccountDto accountDto = new AccountDto(accountResult);
//
////        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);
//
//        return new ResponseEntity(fieldResult, new HttpHeaders(), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/fieldlist", method = RequestMethod.POST)
//    public ResponseEntity findAllFieldsWithOwnerData() {
//        List<FieldRowModelDto> fieldListResult = fieldRepository.findAllFieldsWithOwnerData();
//        return new ResponseEntity(fieldListResult, new HttpHeaders(), HttpStatus.OK);
//    }

}
