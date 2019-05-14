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
public class SwinePassportDto {
    private Long id;
    private String number;
    private Date creationDate;
    private Animal swine;
    private String swineNumber;
    private Herd herd;
    private Owner owner;
    private Date birthDate;
    private String birthPlace;
    private SexType sexType;
//    private Dictionary raceType;
    private String motherNumber;
    private Date deadDate;
    private String description;


public SwinePassportDto(SwinePassport swinePassport) {
	this.id = swinePassport.getId();
	this.number = swinePassport.getNumber();
	this.creationDate = swinePassport.getCreationDate();
	this.swine = swinePassport.getSwine();
	this.swineNumber = swinePassport.getSwineNumber();
	this.herd = swinePassport.getHerd();
	this.owner = swinePassport.getOwner();
	this.birthDate = swinePassport.getBirthDate();
	this.birthPlace = swinePassport.getBirthPlace();
	this.sexType = swinePassport.getSexType();
//	this.raceType = swinePassport.getRaceType();
	this.motherNumber = swinePassport.getMotherNumber();
	this.deadDate = swinePassport.getDeadDate();
	this.description = swinePassport.getDescription();
}

public SwinePassport toEntity() {
	SwinePassport swinePassport = new SwinePassport();
	swinePassport.setId(this.id);
	swinePassport.setNumber(this.number);
	swinePassport.setCreationDate(this.creationDate);
	swinePassport.setSwine(this.swine);
	swinePassport.setSwineNumber(this.swineNumber);
	swinePassport.setHerd(this.herd);
	swinePassport.setOwner(this.owner);
	swinePassport.setBirthDate(this.birthDate);
	swinePassport.setBirthPlace(this.birthPlace);
	swinePassport.setSexType(this.sexType);
//	swinePassport.setRaceType(this.raceType);
	swinePassport.setMotherNumber(this.motherNumber);
	swinePassport.setDeadDate(this.deadDate);
	swinePassport.setDescription(this.description);
	return swinePassport;
}
}
