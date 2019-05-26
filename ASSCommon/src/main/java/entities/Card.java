package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CARD_0005")
public class Card {
    @Id
    @Column(name = "id")
    private String id;
    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;
}
