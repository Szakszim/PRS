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
@Table(name = "GEN_FARM_0007")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME_1")
    private String name1;
    @Column(name = "NAME_2")
    private String name2;
    @Column(name = "FARM_NUMBER")
    private String farmNumber;
    @ManyToOne()
    @JoinColumn(name = "OWNER")
    private Owner owner;
    @ManyToOne()
    @JoinColumn(name = "ADDRESS")
    private Address address;
    @Column(name = "LATITUDE")
    private BigDecimal latitude;
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

}