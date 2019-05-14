package entities;

import enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GEN_ACCOUNT_0005")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "UNIQUE_ID")
    private String uniqueId;
    //    @ManyToOne()
//    @JoinColumn(name = "ACCOUNT_TYPE")
//    private Dictionary accountType;
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNT_TYPE")
    private AccountType accountType;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PASSWORD_EXPIRED_DATE")
    private Date passwordExpiredDate;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "REGISTRATION_CONFIRMATION_INDICATOR")
    private Boolean registrationConfirmationIndicator;
    @Column(name = "ACTIVITY_INDICATOR")
    private Boolean activityIndicator;
    @ManyToOne()
    @JoinColumn(name = "PERSON")
    private Person person;

}