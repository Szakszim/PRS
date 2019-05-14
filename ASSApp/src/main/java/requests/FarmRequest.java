package requests;

import dtos.rowmodels.FarmRowModelDto;
import entities.Farm;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class FarmRequest {

    public Farm save(Farm farm) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Farm> requestUpdate = new HttpEntity<>(farm, null);
        ResponseEntity<Farm> result = restTemplate.exchange(Settings.URL + "savefarm", HttpMethod.POST, requestUpdate, Farm.class);
        farm = result.getBody();
        return farm;
    }

    public Farm getFarmDataByAccountId(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);

        ResponseEntity<Farm> response = restTemplate.exchange(Settings.URL+"farmbyaccount", HttpMethod.POST, requestUpdate, Farm.class);
       Farm farm = response.getBody();

        return farm;
    }

    public List<FarmRowModelDto> findAllFarmsWithOwnerData(){
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<List<FarmRowModelDto> > response = restTemplate.exchange(Settings.URL+"farmlist", HttpMethod.POST, null,new ParameterizedTypeReference<List<FarmRowModelDto>>(){});
        List<FarmRowModelDto> farmListResult = response.getBody();

        return farmListResult;
    }

}
