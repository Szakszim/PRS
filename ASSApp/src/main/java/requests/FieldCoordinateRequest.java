package requests;

import entities.Field;
import entities.FieldCoordinate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class FieldCoordinateRequest {

    public List<FieldCoordinate> save(List<FieldCoordinate> fieldCoordinates) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List<FieldCoordinate>> requestUpdate = new HttpEntity<>(fieldCoordinates, null);
        ResponseEntity<List<FieldCoordinate>> result = restTemplate.exchange(Settings.URL + "savefieldcoordinate", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<FieldCoordinate>>(){});
        fieldCoordinates = result.getBody();
        return fieldCoordinates;
    }

    public List<List<FieldCoordinate>> findAllCoordinatesByFarmId(Long farmId){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> requestUpdate = new HttpEntity<>(farmId, null);
        ResponseEntity<List<List<FieldCoordinate>>> result = restTemplate.exchange(Settings.URL + "fieldcoordinatesbyfarmid", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<List<FieldCoordinate>>>(){});
        List<List<FieldCoordinate>> response = result.getBody();
        return response;
    }

//    public Field getFieldDataByAccountId(Long accountId) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);
//
//        ResponseEntity<Field> response = restTemplate.exchange(Settings.URL+"fieldbyaccount", HttpMethod.POST, requestUpdate, Field.class);
//       Field field = response.getBody();
//
//        return field;
//    }
//
//    public List<FieldRowModelDto> findAllFieldsWithOwnerData(){
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        ResponseEntity<List<FieldRowModelDto> > response = restTemplate.exchange(Settings.URL+"fieldlist", HttpMethod.POST, null,new ParameterizedTypeReference<List<FieldRowModelDto>>(){});
//        List<FieldRowModelDto> fieldListResult = response.getBody();
//
//        return fieldListResult;
//    }

}
