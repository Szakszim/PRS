package requests;

import dtos.CardDto;
import entities.Card;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class CardRequest {
    public List<CardDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CardDto>> response = restTemplate.exchange(
                Settings.URL + "/cards",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {
                });
        return response.getBody();
    }

    public CardDto get(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CardDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/card/" + id, CardDto.class);
        return responseEntity.getBody();
    }

    public CardDto save(CardDto cardDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CardDto> requestUpdate = new HttpEntity<>(cardDto, null);
        ResponseEntity<CardDto> response = restTemplate.exchange(Settings.URL + "/card", HttpMethod.POST, requestUpdate, CardDto.class);
        return response.getBody();
    }

    public void delete(Card card) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Card> requestUpdate = new HttpEntity<>(card, null);
        restTemplate.exchange(Settings.URL + "/card/" + card.getId(), HttpMethod.DELETE, requestUpdate, Card.class);
    }
}
