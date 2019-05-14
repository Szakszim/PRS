package server.controllers;

import dtos.base.NotificationDto;
import dtos.rowmodels.KeyAssignmentRowModelDto;
import entities.*;
import enums.SenderType;
import helpers.LangHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import server.repositories.ConnectionRepository;
import server.repositories.KeyAssignmentRepository;
import server.repositories.KeyRepository;
import utils.DateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class KeyAssignmentController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private KeyAssignmentRepository keyAssignmentRepository;

    @Inject
    private ConnectionRepository connectionRepository;


    @RequestMapping(value = "/keyassignmentlist", method = RequestMethod.POST)
    public ResponseEntity findAllKeysWithFarmAndPurchaserData() {
        List<KeyAssignmentRowModelDto> keyListResult = keyAssignmentRepository.findAllKeysWithFarmAndPurchaserData();
        return new ResponseEntity(keyListResult, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/confirmkeyassignment", method = RequestMethod.POST)
    public ResponseEntity confirmKeyAssignment(@RequestBody @Valid Long keyAssignmentId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        KeyAssignment keyAssignmentResult = keyAssignmentRepository.findById(keyAssignmentId).get();
        keyAssignmentResult.setConfirmationIndicator(Boolean.TRUE);
        keyAssignmentResult = em.merge(keyAssignmentResult);
//        em.flush();


        //create a notification for module welcome message start
        Notification notification = new Notification();
        String topic = "key_assignment.welcome_message.topic_"+keyAssignmentResult.getModuleType().getValue();
        notification.setTopic(topic);
        String message = "key_assignment.welcome_message.message_"+keyAssignmentResult.getModuleType().getValue();
        notification.setMessage(message);
        notification.setDate(new Date());
        notification.setSenderType(SenderType.SYSTEM);
        String sender = "key_assignment.welcome_message.sender_"+ SenderType.SYSTEM.getValue();
        notification.setSender(sender);

        notification = em.merge(notification);

        List<Connection> connections = connectionRepository.findAllConnectionsByFarmId(keyAssignmentResult.getFarm().getId());

        List<NotificationTarget> notificationTargets = new ArrayList<>();

        for(int i=0; i <connections.size();i++) {

            NotificationTarget notificationTarget = new NotificationTarget();
            notificationTarget.setNotification(notification);
            notificationTarget.setRecipient(connections.get(i).getAccount());//??hack?
            notificationTarget.setFarm(keyAssignmentResult.getFarm());
            notificationTarget.setReadingIndicator(Boolean.FALSE);
            notificationTargets.add(notificationTarget);
            em.merge(notificationTarget);
//            notificationTarget.set(keyAssignmentResult.getModuleType());
//            notificationTarget.set(connections.get(i).getConnectionType());
        }

//       em.merge(notificationTargets);
        //create a notification for module welcome message end

        em.flush();

        return new ResponseEntity(keyAssignmentResult, new HttpHeaders(), HttpStatus.OK);
    }
}
