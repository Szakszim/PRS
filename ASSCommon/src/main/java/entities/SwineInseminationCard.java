package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANI_SWINE_INSEMINATION_CARD_0032")
public class SwineInseminationCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "SWINE")
    private Animal swine;
    @Column(name = "INSEMINATION_SEQUENCE_NUMBER")
    private Integer inseminationSequenceNumber;
    @Column(name = "FIRST_INTERVENTION_DATE")
    private Date firstInterventionDate;
    @Column(name = "SECOND_INTERVENTION_DATE")
    private Date secondInterventionDate;
    @Column(name = "THIRD_INTERVENTION_DATE")
    private Date thirdInterventionDate;
    @Column(name = "PARTURITION_DATE")
    private Date parturitionDate;
    @Column(name = "MEN_PIGLETS_QUANTITY")
    private Integer menPigletsQuantity;
    @Column(name = "WOMEN_PIGLETS_QUANTITY")
    private Integer womenPigletsQuantity;
    @Column(name = "DEAD_PIGLETS_QUANTITY")
    private Integer deadPigletsQuantity;
    @Column(name = "SEPARATION_DATE")
    private Date separationDate;
    @Column(name = "SEPARATED_PIGLETS_QUANTITY")
    private Integer separatedPigletsQuantity;
    @Column(name = "COMMENT")
    private String comment;
}