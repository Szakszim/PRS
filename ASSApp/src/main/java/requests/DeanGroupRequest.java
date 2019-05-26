package requests;

import dtos.DeanGroupDto;
import entities.DeanGroup;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class DeanGroupRequest {
    public List<DeanGroupDto> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DeanGroupDto>> response = restTemplate.exchange(
                Settings.URL + "/deanGroups",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeanGroupDto>>() {
                });
        return response.getBody();
    }

    public DeanGroupDto get(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeanGroupDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/deanGroup/" + id, DeanGroupDto.class);
        return responseEntity.getBody();
    }

    public DeanGroup save(DeanGroup deanGroup) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DeanGroup> requestUpdate = new HttpEntity<>(deanGroup, null);
        ResponseEntity<DeanGroup> response = restTemplate.exchange(Settings.URL + "/deanGroup", HttpMethod.POST, requestUpdate, DeanGroup.class);
        return response.getBody();
    }

    public void delete(DeanGroup deanGroup) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DeanGroup> requestUpdate = new HttpEntity<>(deanGroup, null);
        restTemplate.exchange(Settings.URL + "/deanGroup/" + deanGroup.getId(), HttpMethod.DELETE, requestUpdate, DeanGroup.class);
    }
}
