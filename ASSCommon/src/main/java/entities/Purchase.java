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
@Table(name = "GEN_PURCHASE_0010")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @ManyToOne()
    @JoinColumn(name = "PURCHASER")
    private Account purchaser;
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;
    @Column(name = "PAYMENT_INDICATOR")
    private Boolean paymentIndicator;
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "MODULE_TYPE")
    private ModuleType moduleType;
}