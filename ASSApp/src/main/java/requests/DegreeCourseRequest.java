package requests;

import dtos.DegreeCourseDto;
import entities.DegreeCourse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class DegreeCourseRequest {
    public List<DegreeCourseDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DegreeCourseDto>> response = restTemplate.exchange(
                Settings.URL + "/degreeCourses",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DegreeCourseDto>>() {
                });
        return response.getBody();
    }

    public DegreeCourseDto get(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DegreeCourseDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/degreeCourse/" + id, DegreeCourseDto.class);
        return responseEntity.getBody();
    }

    public DegreeCourse save(DegreeCourse degreeCourse) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DegreeCourse> requestUpdate = new HttpEntity<>(degreeCourse, null);
        ResponseEntity<DegreeCourse> response = restTemplate.exchange(Settings.URL + "/degreeCourse", HttpMethod.POST, requestUpdate, DegreeCourse.class);
        return response.getBody();
    }

    public void delete(DegreeCourse degreeCourse) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DegreeCourse> requestUpdate = new HttpEntity<>(degreeCourse, null);
        restTemplate.exchange(Settings.URL + "/degreeCourse/" + degreeCourse.getId(), HttpMethod.DELETE, requestUpdate, DegreeCourse.class);
    }
}
