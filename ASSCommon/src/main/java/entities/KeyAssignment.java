package entities;

import enums.ModuleType;
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
@Table(name = "GEN_KEY_ASSIGNMENT_0011")
public class KeyAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "LICENSE_KEY")
    private Key licenseKey;
    @Column(name = "EXPIRED_DATE")
    private Date licenseExpiredDate;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Enumerated(EnumType.STRING)
    @Column(name = "MODULE_TYPE")
    private ModuleType moduleType;
    @Column(name = "CONFIRMATION_INDICATOR")
    private Boolean confirmationIndicator;
    @ManyToOne()
    @JoinColumn(name = "PURCHASE")
    private Purchase purchase;


}