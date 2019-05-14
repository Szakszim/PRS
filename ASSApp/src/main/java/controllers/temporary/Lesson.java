package controllers.temporary;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private String subject;
    private String room;
    private String hour;
    private String date;
    private Integer frequency;
}
