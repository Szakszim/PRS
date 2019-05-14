package dtos.base;

import entities.Farm;
import entities.Garage;
import entities.Machine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineDto {
    private Long id;
    private Farm farm;
    private String name;
    private String brand;
    private String model;
    private Date productionDate;
    private Date purchaseDate;
    private BigDecimal value;
    private Integer enginePower;
    private BigDecimal manHour;
    private Date overviewFrom;
    private Date overviewUntil;
    private Date insuranceFrom;
    private Date insuranceUntil;
    private Garage garage;

    public MachineDto(Machine machine)
    {
        this.id = machine.getId();
        this.farm = machine.getFarm();
        this.name = machine.getName();
        this.brand = machine.getBrand();
        this.model = machine.getModel();
        this.productionDate = machine.getProductionDate();
        this.purchaseDate = machine.getPurchaseDate();
        this.value = machine.getValue();
        this.enginePower = machine.getEnginePower();
        this.manHour = machine.getManHour();
        this.overviewFrom = machine.getOverviewFrom();
        this.overviewUntil = machine.getOverviewUntil();
        this.insuranceFrom = machine.getInsuranceFrom();
        this.insuranceUntil = machine.getInsuranceUntil();
        this.garage = machine.getGarage();
    }

    public Machine toEntity()
    {
        Machine result = new Machine();
        result.setId(this.id);
        result.setFarm(this.farm);
        result.setName(this.name);
        result.setBrand(this.brand);
        result.setModel(this.model);
        result.setProductionDate(this.productionDate);
        result.setPurchaseDate(this.purchaseDate);
        result.setValue(this.value);
        result.setEnginePower(this.enginePower);
        result.setManHour(this.manHour);
        result.setOverviewFrom(this.overviewFrom);
        result.setOverviewUntil(this.overviewUntil);
        result.setInsuranceFrom(this.insuranceFrom);
        result.setInsuranceUntil(this.insuranceUntil);
        result.setGarage(this.garage);
        return  result;
    }
}
