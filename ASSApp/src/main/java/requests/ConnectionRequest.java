package requests;

import entities.Connection;
import entities.Dictionary;
import entities.Owner;
import enums.DictionaryType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class ConnectionRequest {

    public Connection save(Connection connection) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Connection> requestUpdate = new HttpEntity<>(connection, null);
        ResponseEntity<Connection> result = restTemplate.exchange(Settings.URL + "saveconnection", HttpMethod.POST, requestUpdate, Connection.class);
        connection = result.getBody();
        return connection;
    }

    public List<Connection> getAllConnectionsByUserId(Long userId){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(userId, null);

        ResponseEntity<List<Connection>> response = restTemplate.exchange(Settings.URL+"connectionsbyuserid", HttpMethod.POST, requestUpdate, new ParameterizedTypeReference<List<Connection>>(){});
        List<Connection> connectionsList = response.getBody();

        return connectionsList;
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
