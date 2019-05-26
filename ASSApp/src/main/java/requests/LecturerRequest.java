package requests;

import dtos.LecturerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class LecturerRequest {
    public LecturerDto get(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String params = "?email=" + email + "&password=" + password;
        ResponseEntity<LecturerDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/lecturer" + params, LecturerDto.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
