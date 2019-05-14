package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GEN_ADDRESS_0002")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @ManyToOne()
    @JoinColumn(name = "STREET_TYPE")
    private Dictionary streetType;
    @Column(name = "STREET")
    private String street;
    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;
    @Column(name = "LOCAL_NUMBER")
    private String localNumber;
    @Column(name = "POST_CODE")
    private String postCode;

    public String toString(){
        if(localNumber!=null&&!localNumber.isEmpty()){
            return city+", "+street+" "+houseNumber+"/"+localNumber;
        }
        return city+", "+street+" "+houseNumber;
    }
}