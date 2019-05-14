package dtos.base;

import entities.Address;
import entities.Contact;
import entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String surname;
    private String pesel;
    private String nip;
    private Date birthDate;
    private Address address;
    private Contact contact;

    public PersonDto(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.secondName = person.getSecondName();
        this.surname = person.getSurname();
        this.pesel = person.getPesel();
        this.nip = person.getNip();
        this.birthDate = person.getBirthDate();
        this.address = person.getAddress();
        this.contact = person.getContact();
    }

    public Person toEntity(){
        Person person = new Person();
        person.setId(this.id);
        person.setFirstName(this.firstName);
        person.setSecondName(this.secondName);
        person.setSurname(this.surname);
        person.setPesel(this.pesel);
        person.setNip(this.nip);
        person.setBirthDate(this.birthDate);
        person.setAddress(this.address);
        person.setContact(this.contact);

        return person;
    }
}
