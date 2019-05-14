package requests;

import entities.Address;
import entities.Owner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class OwnerRequest {

    public Owner save(Owner owner) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Owner> requestUpdate = new HttpEntity<>(owner, null);
        ResponseEntity<Owner> result = restTemplate.exchange(Settings.URL + "saveowner", HttpMethod.POST, requestUpdate, Owner.class);
        owner = result.getBody();
        return owner;
    }

//    public Address findById(Long addressId) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Long> requestUpdate = new HttpEntity<>(addressId, null);
//
//        ResponseEntity<Address> response = restTemplate.exchange(Settings.URL + "address", HttpMethod.POST, requestUpdate, Address.class);
//        Address address = response.getBody();
//
//        return address;
//    }


//    public void delete(Address address) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Address> requestUpdate = new HttpEntity<>(address, null);
//        restTemplate.exchange(Settings.URL+"deleteaddress", HttpMethod.DELETE, requestUpdate, Address.class);
//    }
}
