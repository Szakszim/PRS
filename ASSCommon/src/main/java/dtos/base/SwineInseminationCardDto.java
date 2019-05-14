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
public class SwineInseminationCardDto {
    private Long id;
    private Animal swine;
    private Integer inseminationSequenceNumber;
    private Date firstInterventionDate;
    private Date secondInterventionDate;
    private Date thirdInterventionDate;
    private Date parturitionDate;
    private Integer menPigletsQuantity;
    private Integer womenPigletsQuantity;
    private Integer deadPigletsQuantity;
    private Date separationDate;
    private Integer separatedPigletsQuantity;
    private String comment;


    public SwineInseminationCardDto(SwineInseminationCard swineInseminationCard) {
        this.id = swineInseminationCard.getId();
        this.swine = swineInseminationCard.getSwine();
        this.inseminationSequenceNumber = swineInseminationCard.getInseminationSequenceNumber();
        this.firstInterventionDate = swineInseminationCard.getFirstInterventionDate();
        this.secondInterventionDate = swineInseminationCard.getSecondInterventionDate();
        this.thirdInterventionDate = swineInseminationCard.getThirdInterventionDate();
        this.parturitionDate = swineInseminationCard.getParturitionDate();
        this.menPigletsQuantity = swineInseminationCard.getMenPigletsQuantity();
        this.womenPigletsQuantity = swineInseminationCard.getWomenPigletsQuantity();
        this.deadPigletsQuantity = swineInseminationCard.getDeadPigletsQuantity();
        this.separationDate = swineInseminationCard.getSeparationDate();
        this.separatedPigletsQuantity = swineInseminationCard.getSeparatedPigletsQuantity();
        this.comment = swineInseminationCard.getComment();
    }


    public SwineInseminationCard toEntity() {
        SwineInseminationCard swineInseminationCard = new SwineInseminationCard();
        swineInseminationCard.setId(this.id);
        swineInseminationCard.setSwine(this.swine);
        swineInseminationCard.setInseminationSequenceNumber(this.inseminationSequenceNumber);
        swineInseminationCard.setFirstInterventionDate(this.firstInterventionDate);
        swineInseminationCard.setSecondInterventionDate(this.secondInterventionDate);
        swineInseminationCard.setThirdInterventionDate(this.thirdInterventionDate);
        swineInseminationCard.setParturitionDate(this.parturitionDate);
        swineInseminationCard.setMenPigletsQuantity(this.menPigletsQuantity);
        swineInseminationCard.setWomenPigletsQuantity(this.womenPigletsQuantity);
        swineInseminationCard.setDeadPigletsQuantity(this.deadPigletsQuantity);
        swineInseminationCard.setSeparationDate(this.separationDate);
        swineInseminationCard.setSeparatedPigletsQuantity(this.separatedPigletsQuantity);
        swineInseminationCard.setComment(this.comment);
        return swineInseminationCard;
    }
}
