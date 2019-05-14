package dtos.base;

import entities.*;
import enums.SexType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CattleInseminationCardDto {
    private Long id;
    private Animal cattle;
    private Integer inseminationSequenceNumber;
    private Date firstInterventionDate;
    private String firstInterventionRace;
    private Date secondInterventionDate;
    private String secondInterventionRace;
    private Date thirdInterventionDate;
    private String thirdInterventionRace;
    private Date fourthInterventionDate;
    private String fourthInterventionRace;
    private Date checkDate;
    private Boolean checkStatus;
    private Date parturitionDate;
    private SexType sexType;
    private String destiny;
    private String comment;

    public CattleInseminationCardDto(CattleInseminationCard cattleInseminationCard) {
        this.id = cattleInseminationCard.getId();
        this.cattle = cattleInseminationCard.getCattle();
        this.inseminationSequenceNumber = cattleInseminationCard.getInseminationSequenceNumber();
        this.firstInterventionDate = cattleInseminationCard.getFirstInterventionDate();
        this.firstInterventionRace = cattleInseminationCard.getFirstInterventionRace();
        this.secondInterventionDate = cattleInseminationCard.getSecondInterventionDate();
        this.secondInterventionRace = cattleInseminationCard.getSecondInterventionRace();
        this.thirdInterventionDate = cattleInseminationCard.getThirdInterventionDate();
        this.thirdInterventionRace = cattleInseminationCard.getThirdInterventionRace();
        this.fourthInterventionDate = cattleInseminationCard.getFourthInterventionDate();
        this.fourthInterventionRace = cattleInseminationCard.getFourthInterventionRace();
        this.checkDate = cattleInseminationCard.getCheckDate();
        this.checkStatus = cattleInseminationCard.getCheckStatus();
        this.parturitionDate = cattleInseminationCard.getParturitionDate();
        this.sexType = cattleInseminationCard.getSexType();
        this.destiny = cattleInseminationCard.getDestiny();
        this.comment = cattleInseminationCard.getComment();
    }

    public CattleInseminationCard toEntity() {
        CattleInseminationCard cattleInseminationCard = new CattleInseminationCard();
        cattleInseminationCard.setId(this.id);
        cattleInseminationCard.setCattle(this.cattle);
        cattleInseminationCard.setInseminationSequenceNumber(this.inseminationSequenceNumber);
        cattleInseminationCard.setFirstInterventionDate(this.firstInterventionDate);
        cattleInseminationCard.setFirstInterventionRace(this.firstInterventionRace);
        cattleInseminationCard.setSecondInterventionDate(this.secondInterventionDate);
        cattleInseminationCard.setSecondInterventionRace(this.secondInterventionRace);
        cattleInseminationCard.setThirdInterventionDate(this.thirdInterventionDate);
        cattleInseminationCard.setThirdInterventionRace(this.thirdInterventionRace);
        cattleInseminationCard.setFourthInterventionDate(this.fourthInterventionDate);
        cattleInseminationCard.setFourthInterventionRace(this.fourthInterventionRace);
        cattleInseminationCard.setCheckDate(this.checkDate);
        cattleInseminationCard.setCheckStatus(this.checkStatus);
        cattleInseminationCard.setParturitionDate(this.parturitionDate);
        cattleInseminationCard.setSexType(this.sexType);
        cattleInseminationCard.setDestiny(this.destiny);
        cattleInseminationCard.setComment(this.comment);
        return cattleInseminationCard;
    }
}
