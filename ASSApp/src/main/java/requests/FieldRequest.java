package requests;

import dtos.other.BasicFieldDataDto;
import entities.Field;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class FieldRequest {

    public Field save(Field field) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Field> requestUpdate = new HttpEntity<>(field, null);
        ResponseEntity<Field> result = restTemplate.exchange(Settings.URL + "savefield", HttpMethod.POST, requestUpdate, Field.class);
        field = result.getBody();
        return field;
    }

    public BasicFieldDataDto getBasicFieldDataByFieldId(Long fieldId){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> requestUpdate = new HttpEntity<>(fieldId, null);
        ResponseEntity<BasicFieldDataDto> result = restTemplate.exchange(Settings.URL + "basicfielddatabyfieldid", HttpMethod.POST, requestUpdate, BasicFieldDataDto.class);
        BasicFieldDataDto response = result.getBody();
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
