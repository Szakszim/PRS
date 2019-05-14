package server.controllers;

import dtos.rowmodels.FarmRowModelDto;
import entities.Farm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.FarmRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class FarmController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private FarmRepository farmRepository;

    @Transactional
    @RequestMapping(value = "/savefarm", method = RequestMethod.POST)
    public ResponseEntity saveFarm(@RequestBody @Valid Farm farm, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        farm.setLatitude(farm.getLatitude().setScale(14, BigDecimal.ROUND_HALF_UP));
        farm.setLongitude(farm.getLongitude().setScale(14, BigDecimal.ROUND_HALF_UP));
        farm = em.merge(farm);
        em.flush();
//        em.getTransaction().commit();
        //tutaj przypisujemy wartość pól z obiektu otrzymanego w zapytaniu
        return new ResponseEntity(farm, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/farmbyaccount", method = RequestMethod.POST)
    public ResponseEntity getFarmDataByAccountId(@RequestBody @Valid Long accountId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Farm farmResult = farmRepository.findFarmByAccountId(accountId);

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(farmResult, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/farmlist", method = RequestMethod.POST)
    public ResponseEntity findAllFarmsWithOwnerData() {
        List<FarmRowModelDto> farmListResult = farmRepository.findAllFarmsWithOwnerData();
        return new ResponseEntity(farmListResult, new HttpHeaders(), HttpStatus.OK);
    }

}
