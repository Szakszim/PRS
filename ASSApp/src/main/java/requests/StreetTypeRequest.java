package requests;

import dtos.rowmodels.AnimalRowModelDto;
import entities.Dictionary;
import enums.DictionaryType;
import lombok.Builder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class StreetTypeRequest extends DictionaryRequest{
    @Override
    public Dictionary findByBusinessCode(String businessCode){
 return null;
    }

    @Override
    public List<Dictionary> getDictionary(){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestUpdate = new HttpEntity<>(DictionaryType.STREET_TYPE.getBusinessKey(), null);

        ResponseEntity<List<Dictionary>> response = restTemplate.exchange(Settings.URL+"dictionarypositions", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<Dictionary>>(){});
        List<Dictionary> positionList = response.getBody();

        return positionList;
    }
}
