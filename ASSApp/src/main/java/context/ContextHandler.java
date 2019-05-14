package context;

//import entities.Company;
//import entities.Division;
//import entities.Employee;
import entities.Account;
import entities.Farm;
import entities.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContextHandler {
    private Account currentAccount;
    private Person currentPerson;
    private Farm currentFarm;

//    private Company company;
//    private Division division;
//    private Employee employee;
}
