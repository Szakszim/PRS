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
public class KeyAssignmentDto {
    private Long id;
    private Key licenseKey;
    private Date licenseExpiredDate;
    private Farm farm;
    private ModuleType moduleType;
    private Purchase purchase;
    private Boolean confirmationIndicator;

    public KeyAssignmentDto(KeyAssignment keyAssignment) {
        this.id = keyAssignment.getId();
        this.licenseKey = keyAssignment.getLicenseKey();
        this.licenseExpiredDate = keyAssignment.getLicenseExpiredDate();
        this.farm = keyAssignment.getFarm();
        this.moduleType = keyAssignment.getModuleType();
        this.purchase = keyAssignment.getPurchase();
        this.confirmationIndicator = keyAssignment.getConfirmationIndicator();
    }

    public KeyAssignment toEntity() {
        KeyAssignment keyAssignment = new KeyAssignment();
        keyAssignment.setId(this.id);
        keyAssignment.setLicenseKey(this.licenseKey);
        keyAssignment.setLicenseExpiredDate(this.licenseExpiredDate);
        keyAssignment.setFarm(this.farm);
        keyAssignment.setModuleType(this.moduleType);
        keyAssignment.setPurchase(this.purchase);
        keyAssignment.setConfirmationIndicator(this.confirmationIndicator);

        return keyAssignment;
    }
}
