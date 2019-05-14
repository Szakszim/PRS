package dtos.base;

import entities.Address;
import entities.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String country;
    private String city;
    private Dictionary streetType;
    private String street;
    private String houseNumber;
    private String localNumber;
    private String postCode;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.streetType = address.getStreetType();
        this.street = address.getStreet();
        this.houseNumber = address.getHouseNumber();
        this.localNumber = address.getLocalNumber();
        this.postCode = address.getPostCode();
    }

    public Address toEntity() {
        Address address = new Address();
        address.setId(this.id);
        address.setCountry(this.country);
        address.setCity(this.city);
        address.setStreetType(this.streetType);
        address.setStreet(this.street);
        address.setHouseNumber(this.houseNumber);
        address.setLocalNumber(this.localNumber);
        address.setPostCode(this.postCode);

        return address;
    }
}
