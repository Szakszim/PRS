package dtos.base;

import entities.Account;
import entities.Connection;
import entities.Dictionary;
import entities.Farm;
import enums.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDto {
    private Long id;
    private Account account;
    private Farm farm;
    private ConnectionType connectionType;
    private Date connectionDate;

    public ConnectionDto(Connection connection) {
        this.id = connection.getId();
        this.account = connection.getAccount();
        this.farm = connection.getFarm();
        this.connectionType = connection.getConnectionType();
        this.connectionDate = connection.getConnectionDate();
    }

    public Connection toEntity() {
        Connection connection = new Connection();
        connection.setId(this.id);
        connection.setAccount(this.account);
        connection.setFarm(this.farm);
        connection.setConnectionType(this.connectionType);
        connection.setConnectionDate(this.connectionDate);

        return connection;
    }
}
