package entities;

import enums.ConnectionType;
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
@Table(name = "GEN_CONNECTION_0008")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "ACCOUNT")
    private Account account;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Enumerated(EnumType.STRING)
    @Column(name = "CONNECTION_TYPE")
    private ConnectionType connectionType;
    @Column(name = "CONNECTION_DATE")
    private Date connectionDate;

    public String toString(){
        return farm.getFarmNumber() + " "+ farm.getName1()+" "+farm.getName2() + " | " + connectionType.toString();
    }
}