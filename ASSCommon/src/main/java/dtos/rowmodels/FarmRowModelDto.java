package dtos.rowmodels;

import entities.Address;
import enums.OwnerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmRowModelDto {

    //farm
    private Long id;
    private String farmName1;
    private String farmName2;
    private String farmNumber;
    private Address farmAddress;
    //owner
    private String uniqueId;
    private OwnerType ownerType;
    private String name1;
    private String name2;
    private String name3;
    private String nip;

}