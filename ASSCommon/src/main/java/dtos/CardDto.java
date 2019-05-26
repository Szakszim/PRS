package dtos;

import entities.Card;
import entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String id;
    private Student student;

    public CardDto(Card card) {
        this.id = card.getId();
        this.student = card.getStudent();
    }

    public Card toEntity() {
        return new Card(id, student);
    }
}
