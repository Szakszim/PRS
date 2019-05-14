package dtos.base;

import entities.*;
import enums.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private String uniqueId;
    private OwnerType ownerType;
    private String name1;
    private String name2;
    private String name3;
    private String nip;
    private Address address;
    private Contact contact;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.uniqueId = owner.getUniqueId();
        this.ownerType = owner.getOwnerType();
        this.name1 = owner.getName1();
        this.name2 = owner.getName2();
        this.name3 = owner.getName3();
        this.nip = owner.getNip();
        this.address = owner.getAddress();
        this.contact = owner.getContact();
    }

    public Owner toEntity() {
        Owner owner = new Owner();
        owner.setId(this.id);
        owner.setUniqueId(this.uniqueId);
        owner.setOwnerType(this.ownerType);
        owner.setName1(this.name1);
        owner.setName2(this.name2);
        owner.setName3(this.name3);
        owner.setNip(this.nip);
        owner.setAddress(this.address);
        owner.setContact(this.contact);

        return owner;
    }
}
