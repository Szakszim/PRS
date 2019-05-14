package controllers.temporary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String cardId;
    private Integer albumNumber;
    private String firstName;
    private String lastName;
    private String faculty;
    private String deanGroup;
    private String eMail;
}
