package requests;

import dtos.rowmodels.AccountRowModelDto;
import entities.Account;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class AccountRequest {

    public Account save(Account account) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Account> requestUpdate = new HttpEntity<>(account, null);
        ResponseEntity<Account> test = restTemplate.exchange(Settings.URL + "saveaccount", HttpMethod.POST, requestUpdate, Account.class);
        account = test.getBody();
        return account;
    }

    public Account findById(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);

        ResponseEntity<Account> response = restTemplate.exchange(Settings.URL + "account", HttpMethod.POST, requestUpdate, Account.class);
        Account account = response.getBody();

        return account;
    }

    public List<AccountRowModelDto> findAllAccountsWithPersonData() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<AccountRowModelDto>> response = restTemplate.exchange(Settings.URL + "accountlist", HttpMethod.POST, null, new ParameterizedTypeReference<List<AccountRowModelDto>>() {
        });
        List<AccountRowModelDto> accountListResult = response.getBody();

        return accountListResult;
    }

    public void delete(Account account) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Account> requestUpdate = new HttpEntity<>(account, null);
        restTemplate.exchange(Settings.URL+"deleteaccount", HttpMethod.DELETE, requestUpdate, Account.class);
    }
}
