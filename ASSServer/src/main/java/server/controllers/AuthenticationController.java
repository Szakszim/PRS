package server.controllers;

import dtos.base.AccountDto;
import dtos.other.AuthenticationContextDto;
import dtos.other.LoginDto;
import dtos.other.ModulePrivilegeDto;
import entities.Account;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AccountRepository;
import server.repositories.KeyRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private KeyRepository keyRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody @Valid LoginDto loginDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Account accountResult = accountRepository.findAccountByCredentials(loginDto.getLogin(), loginDto.getPassword());

        Boolean loginStatus;// = Boolean.FALSE;
        String message;// = "Login or password is incorrect!";
        AuthenticationContextDto authenticationContextDto;

        if (accountResult != null) {
            loginStatus = Boolean.TRUE;
            message = "Login successfully!";
            authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountType(), loginStatus, message);
        }else{
            loginStatus = Boolean.FALSE;
            message = "Login or password is incorrect!";
            authenticationContextDto = new AuthenticationContextDto(null, null, loginStatus, message);
        }


//        AuthenticationContextDto authenticationContextDto = new AuthenticationContextDto(accountResult.getId(), accountResult.getAccountTypeBK(), loginStatus, message);

        return new ResponseEntity(authenticationContextDto, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/moduleprivilege", method = RequestMethod.POST)
    public ResponseEntity getModulePrivilegesByFarmId(@RequestBody @Valid Long farmId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

       ModulePrivilegeDto privileges = keyRepository.getModulePrivilegesByFarmId(farmId);

        return new ResponseEntity(privileges, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/emailavailability", method = RequestMethod.POST)
    public ResponseEntity checkEmailAvailability(@RequestBody @Valid String mail, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

       Boolean response = accountRepository.checkEmailAvailability(mail);

        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/usernameavailability", method = RequestMethod.POST)
    public ResponseEntity checkUsernameAvailability(@RequestBody @Valid String username, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Boolean response = accountRepository.checkUsernameAvailability(username);

        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/accounttype")
    public Account checkAccountType() {

        return null;
    }

}
