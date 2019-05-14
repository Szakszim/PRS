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
public class KeyRowModelDto {

    private Long id;
    private String value;
    private Date creationDate;
    private Boolean activityIndicator;
    private Date keyExpiredDate;
    private Boolean usageIndicator;
    private Date usageDate;

}