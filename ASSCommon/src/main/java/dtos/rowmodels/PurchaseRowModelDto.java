package dtos.rowmodels;

import enums.AccountType;
import enums.ModuleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.StringUtil;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseRowModelDto {

    private Long id;
    private String farmName;
    private String farmNumber;
    private String purchaserName;
    private String uniqueId;
    private ModuleType moduleType;
    private Date purchaseDate;
    private Boolean paymentIndicator;
    private Date paymentDate;

    public PurchaseRowModelDto(Long id, String farmName1, String farmName2, String farmNumber, String purchaserName1, String purchaserName2, String purchaserName3, String uniqueId, ModuleType moduleType, Date purchaseDate, Boolean paymentIndicator, Date paymentDate) {
        this.id = id;
        this.farmName = StringUtil.joinStringsWithDelimiter(" ",farmName1, farmName2);
        this.farmNumber = farmNumber;
        this.purchaserName = StringUtil.joinStringsWithDelimiter(" ",purchaserName1, purchaserName2, purchaserName3);
        this.uniqueId = uniqueId;
        this.moduleType = moduleType;
        this.purchaseDate = purchaseDate;
        this.paymentIndicator = paymentIndicator;
        this.paymentDate = paymentDate;

    }
}