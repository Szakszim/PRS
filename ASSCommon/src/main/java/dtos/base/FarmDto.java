package dtos.base;

import entities.Address;
import entities.Farm;
import entities.Owner;
import entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
    private Long id;
    private String name1;
    private String name2;
    private String farmNumber;
    private Owner owner;
    private Address address;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public FarmDto(Farm farm){
        this.id = farm.getId();
        this.name1 = farm.getName1();
        this.name2 = farm.getName2();
        this.farmNumber = farm.getFarmNumber();
        this.owner = farm.getOwner();
        this.address = farm.getAddress();
        this.latitude = farm.getLatitude();
        this.longitude = farm.getLongitude();
    }

    public Farm toEntity(){
        Farm farm = new Farm();
        farm.setId(this.id);
        farm.setName1(this.name1);
        farm.setName2(this.name2);
        farm.setFarmNumber(this.farmNumber);
        farm.setOwner(this.owner);
        farm.setAddress(this.address);
        farm.setLatitude(this.latitude);
        farm.setLongitude(this.longitude);

        return farm;
    }
}
