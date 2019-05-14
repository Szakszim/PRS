package services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class ConnectionService {

    public Boolean checkConnection() {
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    Settings.URL+"hello",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<String>() {
                    });
            String result = response.getBody();
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
