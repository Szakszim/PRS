package requests;

import dtos.rowmodels.KeyAssignmentRowModelDto;
import entities.KeyAssignment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class KeyAssignmentRequest {

    public List<KeyAssignmentRowModelDto> findAllKeysWithFarmAndPurchaserData(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<KeyAssignmentRowModelDto> > response = restTemplate.exchange(Settings.URL+"keyassignmentlist", HttpMethod.POST, null,new ParameterizedTypeReference<List<KeyAssignmentRowModelDto>>(){});
        List<KeyAssignmentRowModelDto> keyListResult = response.getBody();

        return keyListResult;
    }

    public KeyAssignment confirmKeyAssignment(Long keyAssignmentId){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(keyAssignmentId, null);

        ResponseEntity<KeyAssignment> response = restTemplate.exchange(Settings.URL + "confirmkeyassignment", HttpMethod.POST, requestUpdate,KeyAssignment.class);
        KeyAssignment keyAssignment = response.getBody();

        return keyAssignment;
    }
}
