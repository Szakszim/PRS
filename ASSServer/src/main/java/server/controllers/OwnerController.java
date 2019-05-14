package server.controllers;

import entities.Owner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.OwnerRepository;
import server.repositories.PersonRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class OwnerController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonRepository personRepository;

    @Inject
    private OwnerRepository ownerRepository;

    @Transactional
    @RequestMapping(value = "/saveowner", method = RequestMethod.POST)
    public ResponseEntity saveOwner(@RequestBody @Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        String ownerUniqueId = ownerRepository.getNextOwnerUniqueId();

        owner.setUniqueId(ownerUniqueId);
       owner = em.merge(owner);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(owner, new HttpHeaders(), HttpStatus.OK);
    }

}
