package dtos.base;

//import entities.Employee;

import entities.Account;
import entities.Dictionary;
import entities.Person;
import enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String uniqueId;
    //    private Dictionary accountType;
    private AccountType accountType;
    private String email;
    private String login;
    private String password;
    private Date passwordExpiredDate;
    private Date creationDate;
    private Boolean registrationConfirmationIndicator;
    private Boolean activityIndicator;
    private Person person;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.uniqueId = account.getUniqueId();
        this.accountType = account.getAccountType();
        this.email = account.getEmail();
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.passwordExpiredDate = account.getPasswordExpiredDate();
        this.creationDate = account.getCreationDate();
        this.registrationConfirmationIndicator = account.getRegistrationConfirmationIndicator();
        this.activityIndicator = account.getActivityIndicator();
        this.person = account.getPerson();
    }

    public Account toEntity() {
        Account account = new Account();
        account.setId(this.id);
        account.setUniqueId(this.uniqueId);
        account.setAccountType(this.accountType);
        account.setEmail(this.email);
        account.setLogin(this.login);
        account.setPassword(this.password);
        account.setPasswordExpiredDate(this.passwordExpiredDate);
        account.setCreationDate(this.creationDate);
        account.setRegistrationConfirmationIndicator(this.registrationConfirmationIndicator);
        account.setActivityIndicator(this.activityIndicator);
        account.setPerson(this.person);

        return account;
    }
}
