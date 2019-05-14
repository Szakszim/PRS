package dtos.other;

import entities.Account;
import entities.Person;
import enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationContextDto {

    private Long accountId;
    private AccountType accountType;
//    private Privilage accountPrivilage;
//    private Person userPersonalData;
    private Boolean loginStatus;
    private String message;

}
