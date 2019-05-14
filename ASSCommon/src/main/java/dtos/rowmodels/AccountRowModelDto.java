package dtos.rowmodels;

import enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AccountRowModelDto {

    private Long id;
    private String uniqueId;
    private String mail;
    private String login;
    private AccountType accountType;
    private Date passwordExpiredDate;
    private String userData;
    private Date creationDate;
    private Boolean registrationConfirmationIndicator;
    private Boolean activityIndicator;

    public AccountRowModelDto(Long id, String uniqueId, String mail, String login, AccountType accountType, Date passwordExpiredDate, String userFirstName, String userSecondName, String userSurename, Date creationDate, Boolean registrationConfirmationIndicator, Boolean activityIndicator) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.mail = mail;
        this.login = login;
        this.accountType = accountType;
        this.passwordExpiredDate = passwordExpiredDate;
        if(userSecondName != null && !userSecondName.isEmpty()){
            this.userData = userFirstName + " " + userSecondName + " " + userSurename;
        }else{
            this.userData = userFirstName + " " + userSurename;
        }

        this.creationDate = creationDate;
        this.registrationConfirmationIndicator = registrationConfirmationIndicator;
        this.activityIndicator = activityIndicator;
    }
}