package entities;

import enums.OwnerType;
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
@Table(name = "GEN_OWNER_0006")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "UNIQUE_ID")
    private String uniqueId;
    @Enumerated(EnumType.STRING)
    @Column(name = "OWNER_TYPE")
    private OwnerType ownerType;
    @Column(name = "NAME_1")
    private String name1;
    @Column(name = "NAME_2")
    private String name2;
    @Column(name = "NAME_3")
    private String name3;
    @Column(name = "NIP")
    private String nip;
    @ManyToOne()
    @JoinColumn(name = "ADDRESS")
    private Address address;
    @ManyToOne()
    @JoinColumn(name = "CONTACT")
    private Contact contact;
}