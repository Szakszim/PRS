package entities;

import enums.SexType;
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
@Table(name = "ANI_CATTLE_INSEMINATION_CARD_0030")
public class CattleInseminationCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "CATTLE")
    private Animal cattle;
    @Column(name = "INSEMINATION_SEQUENCE_NUMBER")
    private Integer inseminationSequenceNumber;
    @Column(name = "FIRST_INTERVENTION_DATE")
    private Date firstInterventionDate;
    @Column(name = "FIRST_INTERVENTION_RACE")
    private String firstInterventionRace;
    @Column(name = "SECOND_INTERVENTION_DATE")
    private Date secondInterventionDate;
    @Column(name = "SECOND_INTERVENTION_RACE")
    private String secondInterventionRace;
    @Column(name = "THIRD_INTERVENTION_DATE")
    private Date thirdInterventionDate;
    @Column(name = "THIRD_INTERVENTION_RACE")
    private String thirdInterventionRace;
    @Column(name = "FOURTH_INTERVENTION_DATE")
    private Date fourthInterventionDate;
    @Column(name = "FOURTH_INTERVENTION_RACE")
    private String fourthInterventionRace;
    @Column(name = "CHECK_DATE")
    private Date checkDate;
    @Column(name = "CHECK_STATUS")
    private Boolean checkStatus;
    @Column(name = "PARTURITION_DATE")
    private Date parturitionDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "SEX_TYPE")
    private SexType sexType;
    @Column(name = "DESTINY")
    private String destiny;
    @Column(name = "COMMENT")
    private String comment;
}