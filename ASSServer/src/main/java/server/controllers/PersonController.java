package server.controllers;

import entities.Account;
import entities.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import server.repositories.PersonRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonRepository personRepository;

    @Transactional
    @RequestMapping(value = "/saveperson", method = RequestMethod.POST)
    public ResponseEntity savePerson(@RequestBody @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        person = em.merge(person);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(person, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/personbyaccount", method = RequestMethod.POST)
    public ResponseEntity findPersonByAccountId(@RequestBody @Valid Long accountId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Person personResult = personRepository.findPersonByAccountId(accountId);

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(personResult, new HttpHeaders(), HttpStatus.OK);
    }

}
