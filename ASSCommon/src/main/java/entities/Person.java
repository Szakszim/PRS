package entities;

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
@Table(name = "GEN_PERSON_0004")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SECOND_NAME")
    private String secondName;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "PESEL")
    private String pesel;
    @Column(name = "NIP")
    private String nip;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @ManyToOne()
    @JoinColumn(name = "ADDRESS")
    private Address address;
    @ManyToOne()
    @JoinColumn(name = "CONTACT")
    private Contact contact;
}