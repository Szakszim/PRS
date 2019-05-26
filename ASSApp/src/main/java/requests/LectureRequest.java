package requests;

import dtos.LectureDto;
import entities.Lecture;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class LectureRequest {
    public List<LectureDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<LectureDto>> response = restTemplate.exchange(
                Settings.URL + "/lectures",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LectureDto>>() {
                });
        return response.getBody();
    }

    public LectureDto get(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LectureDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/lecture/" + id, LectureDto.class);
        return responseEntity.getBody();
    }

    public Lecture save(Lecture lecture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Lecture> requestUpdate = new HttpEntity<>(lecture, null);
        ResponseEntity<Lecture> response = restTemplate.exchange(Settings.URL + "/lecture", HttpMethod.POST, requestUpdate, Lecture.class);
        return response.getBody();
    }

    public void delete(Lecture lecture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Lecture> requestUpdate = new HttpEntity<>(lecture, null);
        restTemplate.exchange(Settings.URL + "/lecture/" + lecture.getId(), HttpMethod.DELETE, requestUpdate, Lecture.class);
    }
}
