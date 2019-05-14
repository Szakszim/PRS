package server.controllers;

import dtos.other.AnimalToDeleteDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.Animal;
import entities.CattlePassport;
import entities.Dictionary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AnimalHallRepository;
import server.repositories.CattlePassportRepository;
import server.repositories.DictionaryRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DictionaryController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private DictionaryRepository dictionaryRepository;
    @Inject
    private CattlePassportRepository cattlePassportRepository;

    @RequestMapping(value = "/dictionarypositions", method = RequestMethod.POST)
    public ResponseEntity getDictionary(@RequestBody @Valid String dictionaryBusinessKey, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        List<Dictionary> dictionaryListResult = dictionaryRepository.getDictionary(dictionaryBusinessKey);

//        AccountDto accountDto = new AccountDto(accountResult);

//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBusinessKey(), loginStatus, message);

        return new ResponseEntity(dictionaryListResult, new HttpHeaders(), HttpStatus.OK);
    }

//    @Transactional
//    @RequestMapping(value = "/deleteanimal", method = RequestMethod.DELETE)
//    public void deleteAnimalByIdAndType(@RequestBody @Valid AnimalToDeleteDto animalToDeleteDto, BindingResult result) {
//
//        if (animalToDeleteDto.getAnimalTypeBK().equals("000")) {
//            Animal cattle = new Animal();
//            cattle.setId(animalToDeleteDto.getId());
//            CattlePassport cattlePassport = cattlePassportRepository.findPassportByCattleId(cattle.getId());
//            em.remove(em.contains(cattlePassport) ? cattlePassport : em.merge(cattlePassport));
//            em.remove(em.contains(cattle) ? cattle : em.merge(cattle));
//        }
//
//    }
}
