package entities;

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
@Table(name = "MAC_GARAGE_0040")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "GARAGE_TYPE")
    private Dictionary garageType;
    @Column(name = "GARAGE_AREA")
    private BigDecimal area;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "FULL_LEVEL")
    private Integer fullLevel;
    @Column(name = "LATITUDE")
    private BigDecimal latitude;
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;
}