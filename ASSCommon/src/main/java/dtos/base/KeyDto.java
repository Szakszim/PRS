package dtos.base;

import entities.Dictionary;
import entities.Key;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KeyDto {
    private Long id;
    private String value;
    private Date creationDate;
    private Boolean activityIndicator;
    private Date keyExpiredDate;
    private Boolean usageIndicator;
    private Date usageDate;

    public KeyDto(Key key) {
        this.id = key.getId();
        this.value = key.getValue();
        this.creationDate = key.getCreationDate();
        this.activityIndicator = key.getActivityIndicator();
        this.keyExpiredDate = key.getKeyExpiredDate();
        this.usageIndicator = key.getUsageIndicator();
        this.usageDate = key.getUsageDate();
    }

    public Key toEntity() {
        Key key = new Key();
        key.setId(this.id);
        key.setValue(this.value);
        key.setCreationDate(this.creationDate);
        key.setActivityIndicator(this.activityIndicator);
        key.setKeyExpiredDate(this.keyExpiredDate);
        key.setUsageIndicator(this.usageIndicator);
        key.setUsageDate(this.usageDate);

        return key;
    }
}
