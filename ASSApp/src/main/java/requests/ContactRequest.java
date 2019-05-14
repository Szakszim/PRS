package requests;

import entities.Contact;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class ContactRequest {

    public Contact save(Contact contact) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Contact> requestUpdate = new HttpEntity<>(contact, null);
        ResponseEntity<Contact> test = restTemplate.exchange(Settings.URL + "savecontact", HttpMethod.POST, requestUpdate, Contact.class);
        contact = test.getBody();
        return contact;
    }

    public Contact findById(Long contactId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(contactId, null);

        ResponseEntity<Contact> response = restTemplate.exchange(Settings.URL + "contact", HttpMethod.POST, requestUpdate, Contact.class);
        Contact contact = response.getBody();

        return contact;
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

    public void delete(Contact contact) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Contact> requestUpdate = new HttpEntity<>(contact, null);
        restTemplate.exchange(Settings.URL+"deletecontact", HttpMethod.DELETE, requestUpdate, Contact.class);
    }
}
