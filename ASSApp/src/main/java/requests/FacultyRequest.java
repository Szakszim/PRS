package requests;

import dtos.FacultyDto;
import entities.Faculty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class FacultyRequest {
    public List<FacultyDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FacultyDto>> response = restTemplate.exchange(
                Settings.URL + "/faculties",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FacultyDto>>() {
                });
        return response.getBody();
    }

    public FacultyDto get(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FacultyDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/faculty/" + id, FacultyDto.class);
        return responseEntity.getBody();
    }

    public FacultyDto save(FacultyDto facultyDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FacultyDto> requestUpdate = new HttpEntity<>(facultyDto, null);
        ResponseEntity<FacultyDto> response = restTemplate.exchange(Settings.URL + "/faculty", HttpMethod.POST, requestUpdate, FacultyDto.class);
        return response.getBody();
    }

    public void delete(Faculty faculty) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Faculty> requestUpdate = new HttpEntity<>(faculty, null);
        restTemplate.exchange(Settings.URL + "/faculty/" + faculty.getId(), HttpMethod.DELETE, requestUpdate, Faculty.class);
    }
}
