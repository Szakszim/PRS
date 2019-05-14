package dtos.base;

import entities.AnimalHall;
import entities.Dictionary;
import entities.Farm;
import entities.Garage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalHallDto {
    private Long id;
    private Farm farm;
    private String name;
    private Dictionary animalType;
    private Integer capacity;
    private Integer fullLevel;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AnimalHallDto(AnimalHall animalHall) {
        this.id = animalHall.getId();
        this.farm = animalHall.getFarm();
        this.name = animalHall.getName();
        this.animalType = animalHall.getAnimalType();
        this.capacity = animalHall.getCapacity();
        this.fullLevel = animalHall.getFullLevel();
        this.latitude = animalHall.getLatitude();
        this.longitude = animalHall.getLongitude();
    }

    public AnimalHall toEntity() {
        AnimalHall animalHall = new AnimalHall();
        animalHall.setId(this.id);
        animalHall.setFarm(this.farm);
        animalHall.setName(this.name);
        animalHall.setAnimalType(this.animalType);
        animalHall.setCapacity(this.capacity);
        animalHall.setFullLevel(this.fullLevel);
        animalHall.setLatitude(this.latitude);
        animalHall.setLongitude(this.longitude);
        return animalHall;
    }
}
