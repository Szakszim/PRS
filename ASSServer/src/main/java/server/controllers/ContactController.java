package server.controllers;

import entities.Contact;
import entities.Person;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.PersonRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ContactController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonRepository personRepository;

    @Transactional
    @RequestMapping(value = "/savecontact", method = RequestMethod.POST)
    public ResponseEntity saveContact(@RequestBody @Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
       contact = em.merge(contact);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(contact, new HttpHeaders(), HttpStatus.OK);
    }

}
