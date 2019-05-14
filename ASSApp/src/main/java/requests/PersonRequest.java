package requests;

import entities.Person;
import entities.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class PersonRequest {

    public Person save(Person person) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> requestUpdate = new HttpEntity<>(person, null);
        ResponseEntity<Person> test = restTemplate.exchange(Settings.URL + "saveperson", HttpMethod.POST, requestUpdate, Person.class);
        person = test.getBody();
        return person;
    }

    public Person findById(Long personId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(personId, null);

        ResponseEntity<Person> response = restTemplate.exchange(Settings.URL + "person", HttpMethod.POST, requestUpdate, Person.class);
        Person person = response.getBody();

        return person;
    }

    public Person getPersonDataByAccountId(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);

        ResponseEntity<Person> response = restTemplate.exchange(Settings.URL+"personbyaccount", HttpMethod.POST, requestUpdate, Person.class);
       Person person = response.getBody();

        return person;
    }

    public void delete(Person person) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Person> requestUpdate = new HttpEntity<>(person, null);
        restTemplate.exchange(Settings.URL+"deleteperson", HttpMethod.DELETE, requestUpdate, Person.class);
    }

}
