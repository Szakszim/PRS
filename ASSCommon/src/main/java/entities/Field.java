package entities;

import enums.PossessionStateType;
import enums.SoilClassType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLA_FIELD_0015")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Column(name = "NAME")
    private String name;
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;
    @Column(name = "AREA")
    private BigDecimal area;
    @ManyToOne()
    @JoinColumn(name = "MEASUREMENT_UNIT")
    private Dictionary measurementUnit;
    @Enumerated(EnumType.STRING)
    @Column(name = "SOIL_CLASS")
    private SoilClassType soilClass;
    @Column(name = "SOIL_ACIDIFICATION")
    private BigDecimal soilAcidification;
    @Column(name = "NITROGEN_RICHNESS")
    private BigDecimal nitrogenRichness;
    @Column(name = "PHOSPHORUS_RICHNESS")
    private BigDecimal phosphorusRichness;
    @Column(name = "POTASSIUM_RICHNESS")
    private BigDecimal potassiumRichness;
    @Column(name = "MAGNESIUM_RICHNESS")
    private BigDecimal magnesiumRichness;
    @Column(name = "SULFUR_RICHNESS")
    private BigDecimal sulfurRichness;
    @Column(name = "[DESCRIPTION]")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "POSSESSION_STATE")
    private PossessionStateType possessionState;
}