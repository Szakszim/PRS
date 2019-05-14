package requests;

import dtos.other.AuthenticationContextDto;
import dtos.base.AccountDto;
import dtos.other.LoginDto;
import dtos.other.ModulePrivilegeDto;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

public class AuthenticationRequest {

    public AuthenticationContextDto loginUser(String login, String password){
        RestTemplate restTemplate = new RestTemplate();

        LoginDto loginDto = new LoginDto(login, password);

        HttpEntity<LoginDto> requestUpdate = new HttpEntity<>(loginDto, null);

        ResponseEntity<AuthenticationContextDto> response = restTemplate.exchange(Settings.URL+"login", HttpMethod.POST, requestUpdate, AuthenticationContextDto.class);
        AuthenticationContextDto authenticationContextDto = response.getBody();

        return authenticationContextDto;
    }

    public ModulePrivilegeDto getModulePrivilegesByFarmId(Long farmId){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(farmId, null);

        ResponseEntity<ModulePrivilegeDto> response = restTemplate.exchange(Settings.URL+"moduleprivilege", HttpMethod.POST, requestUpdate, ModulePrivilegeDto.class);
        ModulePrivilegeDto privileges = response.getBody();

        return privileges;
    }

    public Boolean checkEmailAvailability(String email){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestUpdate = new HttpEntity<>(email, null);

        ResponseEntity<Boolean> response = restTemplate.exchange(Settings.URL+"emailavailability", HttpMethod.POST, requestUpdate, Boolean.class);
        Boolean result = response.getBody();

        return result;
    }


    public Boolean checkUsernameAvailability(String username){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> requestUpdate = new HttpEntity<>(username, null);

        ResponseEntity<Boolean> response = restTemplate.exchange(Settings.URL+"usernameavailability", HttpMethod.POST, requestUpdate, Boolean.class);
        Boolean result = response.getBody();

        return result;
    }
}
