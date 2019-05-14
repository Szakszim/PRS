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
public class BasicFieldDataDto {
    private Long id;
    private String name;
    private String registrationNumber;
    private BigDecimal area;
    private Dictionary measurementUnit;
    private PossessionStateType possessionState;
    private String description;
}
