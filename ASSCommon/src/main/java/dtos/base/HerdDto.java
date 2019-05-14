package dtos.base;

import entities.Address;
import entities.Farm;
import entities.Herd;
import entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HerdDto {
    private Long id;
    private String number;
    private Farm farm;
    private Owner owner;
    private Address address;

    public HerdDto(Herd herd){
        this.id = herd.getId();
        this.number = herd.getNumber();
        this.farm = herd.getFarm();
        this.owner = farm.getOwner();
        this.address = farm.getAddress();
    }

    public Herd toEntity(){
        Herd herd = new Herd();
        herd.setId(this.id);
        herd.setNumber(this.number);
        herd.setFarm(this.farm);
        herd.setOwner(this.owner);
        herd.setAddress(this.address);

        return herd;
    }
}
