package dtos.base;

import entities.*;
import enums.ModuleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private Farm farm;
    private Account purchaser;
    private Date purchaseDate;
    private Boolean paymentIndicator;
    private Date paymentDate;
    private ModuleType moduleType;

    public PurchaseDto(Purchase purchase) {
        this.id = purchase.getId();
        this.farm = purchase.getFarm();
        this.purchaser = purchase.getPurchaser();
        this.purchaseDate = purchase.getPurchaseDate();
        this.paymentIndicator = purchase.getPaymentIndicator();
        this.paymentDate = purchase.getPaymentDate();
        this.moduleType = purchase.getModuleType();
    }

    public Purchase toEntity() {
        Purchase purchase = new Purchase();
        purchase.setId(this.id);
        purchase.setFarm(this.farm);
        purchase.setPurchaser(this.purchaser);
        purchase.setPurchaseDate(this.purchaseDate);
        purchase.setPaymentIndicator(this.paymentIndicator);
        purchase.setPurchaseDate(this.paymentDate);
        purchase.setModuleType(this.moduleType);

        return purchase;
    }
}
