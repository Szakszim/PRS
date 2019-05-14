package server.controllers;

import entities.Connection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.ConnectionRepository;
import server.repositories.PersonRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ConnectionController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonRepository personRepository;

    @Inject
    private ConnectionRepository connectionRepository;

    @Transactional
    @RequestMapping(value = "/saveconnection", method = RequestMethod.POST)
    public ResponseEntity saveConnection(@RequestBody @Valid Connection connection, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        connection.setConnectionDate(new Date());
       connection = em.merge(connection);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(connection, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/connectionsbyuserid", method = RequestMethod.POST)
    public ResponseEntity getAllConnectionsByUserId(@RequestBody @Valid Long userId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<Connection> connectionListResult = connectionRepository.getAllConnectionsByUserId(userId);

        return new ResponseEntity(connectionListResult, new HttpHeaders(), HttpStatus.OK);
    }

}
