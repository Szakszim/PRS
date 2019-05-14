package requests;

import dtos.other.AnimalToDeleteDto;
import dtos.other.BasicHallDataDto;
import dtos.rowmodels.AnimalRowModelDto;
import entities.AnimalHall;
import entities.Farm;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class AnimalHallRequest {

    public AnimalHall save(AnimalHall animalHall) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AnimalHall> requestUpdate = new HttpEntity<>(animalHall, null);
        ResponseEntity<AnimalHall> result = restTemplate.exchange(Settings.URL + "saveanimalhall", HttpMethod.POST, requestUpdate, AnimalHall.class);
        animalHall = result.getBody();
        return animalHall;
    }

    public List<AnimalRowModelDto> getAnimalListByFarmId(Long farmId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(farmId, null);

        ResponseEntity<List<AnimalRowModelDto>> response = restTemplate.exchange(Settings.URL+"animallistbyfarm", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<AnimalRowModelDto>>(){});
       List<AnimalRowModelDto> animalList = response.getBody();

        return animalList;
    }

    public List<BasicHallDataDto> findAllCoordinatesByFarmId(Long farmId){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(farmId, null);

        ResponseEntity<List<BasicHallDataDto>> response = restTemplate.exchange(Settings.URL+"basichalldatabyfarmid", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<BasicHallDataDto>>(){});
        List<BasicHallDataDto> result = response.getBody();

        return result;
    }

    public void deleteAnimalByIdAndType(AnimalToDeleteDto animalToDeleteDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<AnimalToDeleteDto> requestUpdate = new HttpEntity<>(animalToDeleteDto, null);

        ResponseEntity<AnimalToDeleteDto> response = restTemplate.exchange(Settings.URL+"deleteanimal", HttpMethod.DELETE, requestUpdate,AnimalToDeleteDto.class);
//        List<AnimalRowModelDto> animalList = response.getBody();

//        return animalList;
    }

}
