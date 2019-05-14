package dtos.base;

import entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private Long id;
    private Herd herd;
    private Farm farm;
    private Dictionary animalType;
    private AnimalHall animalHall;

    public AnimalDto(Animal animal) {
        this.id = animal.getId();
        this.herd = animal.getHerd();
        this.farm = animal.getFarm();
        this.animalType = animal.getAnimalType();
        this.animalHall = animal.getAnimalHall();
    }

    public Animal toEntity() {
        Animal animal = new Animal();
        animal.setId(this.id);
        animal.setHerd(this.herd);
        animal.setFarm(this.farm);
        animal.setAnimalType(this.animalType);
        animal.setAnimalHall(this.animalHall);
        return animal;
    }
}
