package dtos.base;

import entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;
    private String countryCode;
    private String code;
    private String phone;
    private String fax;
    private String email;
    private String cellphone;

    public ContactDto(Contact contact){
        this.id = contact.getId();
        this.countryCode=contact.getCountryCode();
        this.code = contact.getCode();
        this.phone=contact.getPhone();
        this.fax =contact.getFax();
        this.email=contact.getEmail();
        this.cellphone=contact.getCellphone();
    }

    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setId(this.id);
        contact.setCountryCode(this.countryCode);
        contact.setCode(this.code);
        contact.setPhone(this.phone);
        contact.setFax(this.fax);
        contact.setEmail(this.email);
        contact.setCellphone(this.cellphone);

        return contact;
    }
}
