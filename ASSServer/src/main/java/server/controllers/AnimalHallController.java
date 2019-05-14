package server.controllers;

import dtos.other.AnimalToDeleteDto;
import dtos.other.BasicHallDataDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import server.repositories.AnimalHallRepository;
import server.repositories.CattlePassportRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class AnimalHallController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AnimalHallRepository animalHallRepository;
    @Inject
    private CattlePassportRepository cattlePassportRepository;


    @Transactional
    @RequestMapping(value = "/saveanimalhall", method = RequestMethod.POST)
    public ResponseEntity saveFarm(@RequestBody @Valid AnimalHall animalHall, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
       animalHall.setLatitude(animalHall.getLatitude().setScale(14, BigDecimal.ROUND_HALF_UP));
        animalHall.setLongitude(animalHall.getLongitude().setScale(14, BigDecimal.ROUND_HALF_UP));
        animalHall = em.merge(animalHall);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(animalHall, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/animallistbyfarm", method = RequestMethod.POST)
    public ResponseEntity getAnimalListByFarmId(@RequestBody @Valid Long farmId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<AnimalRowModelDto> animalListResult = animalHallRepository.getAnimalListByFarmId(farmId);

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(animalListResult, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/basichalldatabyfarmid", method = RequestMethod.POST)
    public ResponseEntity findAllCoordinatesByFarmId(@RequestBody @Valid Long farmId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<BasicHallDataDto> hallListResult = animalHallRepository.findAllCoordinatesByFarmId(farmId);

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(hallListResult, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/deleteanimal", method = RequestMethod.DELETE)
    public void deleteAnimalByIdAndType(@RequestBody @Valid AnimalToDeleteDto animalToDeleteDto, BindingResult result) {

        if (animalToDeleteDto.getAnimalTypeBK().equals("000")) {
            Animal cattle = new Animal();
            cattle.setId(animalToDeleteDto.getId());
            CattlePassport cattlePassport = cattlePassportRepository.findPassportByCattleId(cattle.getId());
            em.remove(em.contains(cattlePassport) ? cattlePassport : em.merge(cattlePassport));
            em.remove(em.contains(cattle) ? cattle : em.merge(cattle));
        }

    }
}
