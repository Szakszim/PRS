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
@Table(name = "ANI_ANIMAL_HALL_0027")
public class AnimalHall {
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
    @JoinColumn(name = "ANIMAL_TYPE")
    private Dictionary animalType;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "FULL_LEVEL")
    private Integer fullLevel;
    @Column(name = "LATITUDE")
    private BigDecimal latitude;
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;
}