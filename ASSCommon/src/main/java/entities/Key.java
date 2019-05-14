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
@Table(name = "GEN_KEY_0009")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "ACTIVITY_INDICATOR")
    private Boolean activityIndicator;
    @Column(name = "KEY_EXPIRED_DATE")
    private Date keyExpiredDate;
    @Column(name = "USAGE_INDICATOR")
    private Boolean usageIndicator;
    @Column(name = "USAGE_DATE")
    private Date usageDate;
}