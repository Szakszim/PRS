package server.controllers;

import dtos.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import server.repositories.CardRepository;
import server.utils.ServerResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    @PostMapping(value = "/card")
    public ResponseEntity saveCard(@RequestBody @Valid CardDto cardDto, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.error();
        } else {
            cardRepository.save(cardDto.toEntity());
            return ServerResponse.positive(cardDto.toEntity());
        }
    }

    @GetMapping(value = "/card/{id}")
    public CardDto getCard(@PathVariable("id") Integer id) {
        return cardRepository.findCardById(id);
    }

    @Transactional
    @DeleteMapping(value = "/card/{id}")
    public void deleteCard(@PathVariable("id") Integer id) {
        cardRepository.deleteCardById(id);
    }

    @GetMapping(value = "/cards")
    public List<CardDto> getCards() {
        return cardRepository.findAllCardsAsDto();
    }


}
