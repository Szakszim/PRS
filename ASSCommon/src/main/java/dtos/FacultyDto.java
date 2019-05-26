package dtos;

import entities.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {
    private Integer id;
    private String facultyAddress;

    public FacultyDto(Faculty faculty) {
        this.id = faculty.getId();
        this.facultyAddress = faculty.getFacultyAddress();
    }

    public Faculty toEntity() {
        return new Faculty(id, facultyAddress);
    }
}
