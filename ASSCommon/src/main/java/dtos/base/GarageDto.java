package dtos.base;

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
public class GarageDto {
    private Long id;
    private Farm farm;
    private String name;
    private Dictionary garageType;
    private BigDecimal garageArea;
    private Integer capacity;
    private Integer fullLevel;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public GarageDto(Garage garage) {
        this.id = garage.getId();
        this.farm = garage.getFarm();
        this.name = garage.getName();
        this.garageType = garage.getGarageType();
        this.garageArea = garage.getArea();
        this.capacity = garage.getCapacity();
        this.fullLevel = garage.getFullLevel();
        this.latitude = garage.getLatitude();
        this.longitude = garage.getLongitude();
    }

    public Garage toEntity() {
        Garage garage = new Garage();
        garage.setId(this.id);
        garage.setFarm(this.farm);
        garage.setName(this.name);
        garage.setGarageType(this.garageType);
        garage.setArea(this.garageArea);
        garage.setCapacity(this.capacity);
        garage.setFullLevel(this.fullLevel);
        garage.setLatitude(this.latitude);
        garage.setLongitude(this.longitude);
        return garage;
    }
}
