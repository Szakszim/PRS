package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANI_ANIMAL_0028")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "HERD")
    private Herd herd;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @ManyToOne()
    @JoinColumn(name = "ANIMAL_TYPE")
    private Dictionary animalType;
    @ManyToOne()
    @JoinColumn(name = "ANIMAL_HALL")
    private AnimalHall animalHall;
}