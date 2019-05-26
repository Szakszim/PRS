package requests;

import dtos.LectureTypeDto;
import entities.LectureType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class LectureTypeRequest {
    public List<LectureTypeDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<LectureTypeDto>> response = restTemplate.exchange(
                Settings.URL + "/lectureTypes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LectureTypeDto>>() {
                });
        return response.getBody();
    }

    public LectureTypeDto get(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LectureTypeDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/lectureType/" + id, LectureTypeDto.class);
        return responseEntity.getBody();
    }

    public LectureType save(LectureType lecture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LectureType> requestUpdate = new HttpEntity<>(lecture, null);
        ResponseEntity<LectureType> response = restTemplate.exchange(Settings.URL + "/lectureType", HttpMethod.POST, requestUpdate, LectureType.class);
        return response.getBody();
    }

    public void delete(LectureType lecture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LectureType> requestUpdate = new HttpEntity<>(lecture, null);
        restTemplate.exchange(Settings.URL + "/lectureType/" + lecture.getId(), HttpMethod.DELETE, requestUpdate, LectureType.class);
    }
}
