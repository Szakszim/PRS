package entities;

import enums.SexType;
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
@Table(name = "ANI_SWINE_PASSPORT_0031")
public class SwinePassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @ManyToOne()
    @JoinColumn(name = "SWINE")
    private Animal swine;
    @Column(name = "SWINE_NUMBER")
    private String swineNumber;
    @ManyToOne()
    @JoinColumn(name = "HERD")
    private Herd herd;
    @ManyToOne()
    @JoinColumn(name = "OWNER")
    private Owner owner;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    @Column(name = "BIRTH_PLACE")
    private String birthPlace;
    @Enumerated(EnumType.STRING)
    @Column(name = "SEX_TYPE")
    private SexType sexType;
//    @ManyToOne()
//    @JoinColumn(name = "RACE_TYPE")
//    private Dictionary raceType;
    @Column(name = "MOTHER_ID")
    private String motherNumber;
    @Column(name = "DEAD_DATE")
    private Date deadDate;
    @Column(name = "DESCRIPTION")
    private String description;
}