package dtos.rowmodels;

import enums.ModuleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.StringUtil;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyAssignmentRowModelDto {

    private Long id;
    private String value;
    private ModuleType moduleType;
    private Date licenseExpiredDate;
    private String farmName;
    private String farmNumber;
    private String purchaserName;
    private Boolean confirmationIndicator;


    public KeyAssignmentRowModelDto(Long id, String value, ModuleType moduleType, Date licenseExpiredDate, String farmName1, String farmName2, String farmNumber, String purchaserFirstName, String purchaserSecondName, String purchaserSurname, Boolean confirmationIndicator) {
        this.id = id;
        this.value = value;
        this.moduleType = moduleType;
        this.licenseExpiredDate = licenseExpiredDate;
        this.farmName = StringUtil.joinStringsWithDelimiter(" ", farmName1, farmName2);
        this.farmNumber = farmNumber;
        this.purchaserName = StringUtil.joinStringsWithDelimiter(" ", purchaserFirstName, purchaserSecondName, purchaserSurname);
        this.confirmationIndicator = confirmationIndicator;
    }
}