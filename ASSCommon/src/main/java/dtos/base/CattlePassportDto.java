package dtos.base;


import entities.*;
import enums.SexType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CattlePassportDto {
    private Long id;
    private String number;
    private Date creationDate;
    private Animal cattle;
    private String cattleNumber;
    private Herd herd;
    private Owner owner;
    private Date birthDate;
    private String birthPlace;
    private SexType sexType;
    private Dictionary raceType;
    private String motherNumber;
    private String ancestryCountry;
    private Boolean ancestryCountryUeMemberIndicator;
    private Date importDate;
    private String oldCattleNumber;
    private Date deadDate;
    private String description;

    public CattlePassportDto(CattlePassport cattlePassport) {
        this.id = cattlePassport.getId();
        this.number = cattlePassport.getNumber();
        this.creationDate = cattlePassport.getCreationDate();
        this.cattle = cattlePassport.getCattle();
        this.cattleNumber = cattlePassport.getCattleNumber();
        this.herd = cattlePassport.getHerd();
        this.owner = cattlePassport.getOwner();
        this.birthDate = cattlePassport.getBirthDate();
        this.birthPlace = cattlePassport.getBirthPlace();
        this.sexType = cattlePassport.getSexType();
        this.raceType = cattlePassport.getRaceType();
        this.motherNumber = cattlePassport.getMotherNumber();
        this.ancestryCountry = cattlePassport.getAncestryCountry();
        this.ancestryCountryUeMemberIndicator = cattlePassport.getAncestryCountryUeMemberIndicator();
        this.importDate = cattlePassport.getImportDate();
        this.oldCattleNumber = cattlePassport.getOldCattleNumber();
        this.deadDate = cattlePassport.getDeadDate();
        this.description = cattlePassport.getDescription();
    }

    public CattlePassport toEntity() {
        CattlePassport cattlePassport = new CattlePassport();
        cattlePassport.setId(this.id);
        cattlePassport.setNumber(this.number);
        cattlePassport.setCreationDate(this.creationDate);
        cattlePassport.setCattle(this.cattle);
        cattlePassport.setCattleNumber(this.cattleNumber);
        cattlePassport.setHerd(this.herd);
        cattlePassport.setOwner(this.owner);
        cattlePassport.setBirthDate(this.birthDate);
        cattlePassport.setBirthPlace(this.birthPlace);
        cattlePassport.setSexType(this.sexType);
        cattlePassport.setRaceType(this.raceType);
        cattlePassport.setMotherNumber(this.motherNumber);
        cattlePassport.setAncestryCountry(this.ancestryCountry);
        cattlePassport.setAncestryCountryUeMemberIndicator(this.ancestryCountryUeMemberIndicator);
        cattlePassport.setImportDate(this.importDate);
        cattlePassport.setOldCattleNumber(this.oldCattleNumber);
        cattlePassport.setDeadDate(this.deadDate);
        cattlePassport.setDescription(this.description);
        return cattlePassport;
    }
}
