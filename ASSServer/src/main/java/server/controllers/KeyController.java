package server.controllers;

import dtos.rowmodels.KeyAssignmentRowModelDto;
import dtos.rowmodels.KeyRowModelDto;
import entities.Account;
import entities.Farm;
import entities.Key;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import server.repositories.KeyRepository;
import server.services.KeyService;
import utils.DateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class KeyController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private KeyRepository keyRepository;

    @Inject
    private KeyService keyService;

    @RequestMapping(value = "/key", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody @Valid Long accountId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account accountResult = accountRepository.findById(accountId).get();

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(accountResult, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/keylist", method = RequestMethod.POST)
    public ResponseEntity findAllKeys() {
        List<KeyRowModelDto> keyListResult = keyRepository.findAllKeys();
        return new ResponseEntity(keyListResult, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/savekey", method = RequestMethod.POST)
    public ResponseEntity saveKey(@RequestBody @Valid Key key, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        key = em.merge(key);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(key, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/generatekey", method = RequestMethod.POST)
    public ResponseEntity generateKey() {

        Key key = new Key();
        key.setValue(keyService.generateKey());
        key.setCreationDate(new Date());
        key.setActivityIndicator(Boolean.TRUE);
        key.setKeyExpiredDate(DateUtil.addDays(new Date(), 30));
        key.setUsageIndicator(Boolean.FALSE);
        key = em.merge(key);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(key, new HttpHeaders(), HttpStatus.OK);
    }

}
