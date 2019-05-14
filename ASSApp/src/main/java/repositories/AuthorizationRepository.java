package repositories;

import dtos.base.AccountDto;
import dtos.other.LoginDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class AuthorizationRepository {
    public AccountDto loginUser(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();

        LoginDto loginDto = new LoginDto(login, password);

        HttpEntity<LoginDto> requestUpdate = new HttpEntity<>(loginDto, null);

        ResponseEntity<AccountDto> test = restTemplate.exchange(Settings.URL+"login", HttpMethod.POST, requestUpdate, AccountDto.class);
        AccountDto accountDto = test.getBody();
        return accountDto;
    }

}
