package dtos.other;

//import entities.Employee;
import entities.Dictionary;
import enums.PossessionStateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicHallDataDto {
    private Long id;
    private String name;
    private Dictionary animalType;
    private Integer capacity;
    private Integer fullLevel;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
