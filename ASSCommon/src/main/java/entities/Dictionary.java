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
@Table(name = "GEN_DICTIONARY_0001")
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "DICTIONARY_TYPE")
    private DictionaryType dictionaryType;
    @Column(name = "BUSINESS_KEY")
    private String businessKey;
    @Column(name = "SYMBOL")
    private String symbol;
    @Column(name = "[VALUE]")
    private String value;
    @Column(name = "DESCRIPTION")
    private String description;

    public String toString(){
        return this.value;
    }

}
