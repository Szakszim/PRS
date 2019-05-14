package dtos.base;

import entities.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FieldWorkDto {
    private Long id;
    private Field field;
    private Date workDate;

    public FieldWorkDto(FieldWork fieldWork) {
        this.id = fieldWork.getId();
        this.field = fieldWork.getField();
        this.workDate = fieldWork.getWorkDate();
    }

    public FieldWork toEntity() {
        FieldWork fieldWork = new FieldWork();
        fieldWork.setId(this.id);
        fieldWork.setField(this.field);
        fieldWork.setWorkDate(this.workDate);
        return fieldWork;
    }
}
