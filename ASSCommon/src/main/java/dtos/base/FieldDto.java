package dtos.base;

import entities.*;
import enums.PossessionStateType;
import enums.SoilClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {
    private Long id;
    private Farm farm;
    private String name;
    private String registrationNumber;
    private BigDecimal area;
    private Dictionary measurementUnit;
    private SoilClassType soilClass;
    private BigDecimal soilAcidification;
    private BigDecimal nitrogenRichness;
    private BigDecimal phosphorusRichness;
    private BigDecimal potassiumRichness;
    private BigDecimal magnesiumRichness;
    private BigDecimal sulfurRichness;
    private String description;
    private PossessionStateType possessionState;

    public FieldDto(Field field) {
        this.id = field.getId();
        this.farm = field.getFarm();
        this.name = field.getName();
        this.area = field.getArea();
        this.measurementUnit = field.getMeasurementUnit();
        this.registrationNumber = field.getRegistrationNumber();
        this.soilClass = field.getSoilClass();
        this.soilAcidification = field.getSoilAcidification();
        this.nitrogenRichness = field.getNitrogenRichness();
        this.phosphorusRichness = field.getPhosphorusRichness();
        this.potassiumRichness = field.getPotassiumRichness();
        this.magnesiumRichness = field.getMagnesiumRichness();
        this.sulfurRichness = field.getSulfurRichness();
        this.description = field.getDescription();
        this.possessionState = field.getPossessionState();
    }

    public Field toEntity() {
        Field field = new Field();
        field.setId(this.id);
        field.setFarm(this.farm);
        field.setName(this.name);
        field.setArea(this.area);
        field.setMeasurementUnit(this.measurementUnit);
        field.setRegistrationNumber(this.registrationNumber);
        field.setSoilClass(this.soilClass);
        field.setSoilAcidification(this.soilAcidification);
        field.setNitrogenRichness(this.nitrogenRichness);
        field.setPhosphorusRichness(this.phosphorusRichness);
        field.setPotassiumRichness(this.potassiumRichness);
        field.setMagnesiumRichness(this.magnesiumRichness);
        field.setSulfurRichness(this.sulfurRichness);
        field.setDescription(this.description);
        field.setPossessionState(this.possessionState);

        return field;
    }
}
