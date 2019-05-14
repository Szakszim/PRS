package server.controllers;

import dtos.rowmodels.AccountRowModelDto;
import entities.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import utils.DateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class AccountController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountRepository accountRepository;

    @Transactional
    @RequestMapping(value = "/saveaccount", method = RequestMethod.POST)
    public ResponseEntity saveAccount(@RequestBody @Valid Account account, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        String accountUniqueId =accountRepository.getNextAccountUniqueId();
        account.setUniqueId(accountUniqueId);
        account.setPasswordExpiredDate(DateUtil.addDays(new Date(),30));
        account.setCreationDate(new Date());
        account.setRegistrationConfirmationIndicator(Boolean.FALSE);
        account.setActivityIndicator(Boolean.TRUE);

        account = em.merge(account);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(account, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity findById(@RequestBody @Valid Long accountId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account accountResult = accountRepository.findById(accountId).get();

        return new ResponseEntity(accountResult, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accountlist", method = RequestMethod.POST)
    public ResponseEntity findAllAccountsWithPersonData() {
        List<AccountRowModelDto> accountListResult = accountRepository.findAllAccountsWithPersonData();
        return new ResponseEntity(accountListResult, new HttpHeaders(), HttpStatus.OK);
    }
}
