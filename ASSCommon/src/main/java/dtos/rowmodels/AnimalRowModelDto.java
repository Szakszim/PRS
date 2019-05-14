package dtos.rowmodels;

import entities.AnimalHall;
import entities.Herd;
import enums.SexType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRowModelDto {

    private Long animalId;
    private String number;
    private SexType sexType;
    private String raceType;
    private String animalType;
    private String motherNumber;
    private Date birthDate;
    private AnimalHall animalHall;
    private String description;

}