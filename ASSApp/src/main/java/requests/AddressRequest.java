package requests;

import dtos.rowmodels.AccountRowModelDto;
import entities.Account;
import entities.Address;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class AddressRequest {

    public Address save(Address address) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Address> requestUpdate = new HttpEntity<>(address, null);
        ResponseEntity<Address> test = restTemplate.exchange(Settings.URL + "saveaddress", HttpMethod.POST, requestUpdate, Address.class);
        address = test.getBody();
        return address;
    }

    public Address findById(Long addressId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(addressId, null);

        ResponseEntity<Address> response = restTemplate.exchange(Settings.URL + "address", HttpMethod.POST, requestUpdate, Address.class);
        Address address = response.getBody();

        return address;
    }

//    public List<AccountRowModelDto> findAllAccountsWithPersonData() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<List<AccountRowModelDto>> response = restTemplate.exchange(Settings.URL + "accountlist", HttpMethod.POST, null, new ParameterizedTypeReference<List<AccountRowModelDto>>() {
//        });
//        List<AccountRowModelDto> accountListResult = response.getBody();
//
//        return accountListResult;
//    }

    public void delete(Address address) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Address> requestUpdate = new HttpEntity<>(address, null);
        restTemplate.exchange(Settings.URL+"deleteaddress", HttpMethod.DELETE, requestUpdate, Address.class);
    }
}
