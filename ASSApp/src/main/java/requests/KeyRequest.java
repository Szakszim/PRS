package requests;

import dtos.rowmodels.KeyAssignmentRowModelDto;
import dtos.rowmodels.KeyRowModelDto;
import entities.Key;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class KeyRequest {

//    public List<KeyAssignmentRowModelDto> findAllKeysWithPurchaserAndFarmData(){
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        ResponseEntity<List<KeyAssignmentRowModelDto> > response = restTemplate.exchange(Settings.URL+"keylist", HttpMethod.POST, null,new ParameterizedTypeReference<List<KeyAssignmentRowModelDto>>(){});
//        List<KeyAssignmentRowModelDto> keyListResult = response.getBody();
//
//        return keyListResult;
//    }

    public List<KeyRowModelDto> findAllKeys(){
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<List<KeyRowModelDto>> response = restTemplate.exchange(Settings.URL+"keylist", HttpMethod.POST, null,new ParameterizedTypeReference<List<KeyRowModelDto>>(){});
        List<KeyRowModelDto> keyListResult = response.getBody();

        return keyListResult;
    }

//    public void generateKey(){
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Object> requestUpdate = new HttpEntity<>(null, null);
//        ResponseEntity<Object> test = restTemplate.exchange(Settings.URL + "generatekey", HttpMethod.POST, requestUpdate,Object.class);
////        account = test.getBody();
////        return account;
//    }

    public void generateKey() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Key> response = restTemplate.exchange(Settings.URL + "generatekey", HttpMethod.POST, null, Key.class);
    }
}
