package dtos.base;

import entities.Field;
import entities.FieldCoordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FieldCoordinateDto {
    private Long id;
    private Field field;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer sequenceNumber;


    public FieldCoordinateDto(FieldCoordinate fieldCoordinate){
        this.id = fieldCoordinate.getId();
        this.field = fieldCoordinate.getField();
        this.latitude = fieldCoordinate.getLatitude();
        this.longitude = fieldCoordinate.getLongitude();
        this.sequenceNumber = fieldCoordinate.getSequenceNumber();
    }

    public FieldCoordinate toEntity(){
        FieldCoordinate fieldCoordinate = new FieldCoordinate();
        fieldCoordinate.setId(this.id);
        fieldCoordinate.setField(this.field);
        fieldCoordinate.setLatitude(this.latitude);
        fieldCoordinate.setLongitude(this.longitude);
        fieldCoordinate.setSequenceNumber(this.sequenceNumber);

        return fieldCoordinate;
    }
}
