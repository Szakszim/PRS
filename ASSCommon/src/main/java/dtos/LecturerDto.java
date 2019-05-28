package dtos;

import entities.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

    public LecturerDto(Lecturer lecturer) {
        this.id = lecturer.getId();
        this.firstName = lecturer.getFirstName();
        this.lastName = lecturer.getLastName();
        this.eMail = lecturer.getEMail();
        this.password = lecturer.getPassword();
    }

    public Lecturer toEntity() {
        return new Lecturer(id, firstName, lastName, eMail, password);
    }
}
