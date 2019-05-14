package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MAC_MACHINE_0041")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "PRODUCTION_DATE")
    private Date productionDate;
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;
    @Column(name = "VALUE")
    private BigDecimal value;
    @Column(name = "ENGINE_POWER")
    private Integer enginePower;
    @Column(name = "MAN_HOUR")
    private BigDecimal manHour;
    @Column(name = "OVERVIEW_FROM")
    private Date overviewFrom;
    @Column(name = "OVERVIEW_UNTIL")
    private Date overviewUntil;
    @Column(name = "INSURANCE_FROM")
    private Date insuranceFrom;
    @Column(name = "INSURANCE_UNTIL")
    private Date insuranceUntil;
    @ManyToOne()
    @JoinColumn(name = "GARAGE")
    private Garage garage;
}
