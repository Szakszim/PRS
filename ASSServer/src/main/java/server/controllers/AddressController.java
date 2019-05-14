package server.controllers;

import entities.Address;
import entities.Contact;
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
public class AddressController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonRepository personRepository;

    @Transactional
    @RequestMapping(value = "/saveaddress", method = RequestMethod.POST)
    public ResponseEntity saveAddress(@RequestBody @Valid Address address, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
       address = em.merge(address);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(address, new HttpHeaders(), HttpStatus.OK);
    }

}
