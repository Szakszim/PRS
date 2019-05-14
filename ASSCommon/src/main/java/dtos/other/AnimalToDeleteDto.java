package dtos.other;

//import entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalToDeleteDto {
    private Long id;
    private String animalTypeBK;
}
