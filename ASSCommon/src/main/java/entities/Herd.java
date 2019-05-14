package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANI_HERD_0026")
public class Herd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMBER")
    private String number;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @ManyToOne()
    @JoinColumn(name = "OWNER")
    private Owner owner;
    @ManyToOne()
    @JoinColumn(name = "ADDRESS")
    private Address address;
}