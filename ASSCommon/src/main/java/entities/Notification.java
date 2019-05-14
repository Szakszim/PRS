package entities;

import enums.SenderType;
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
@Table(name = "GEN_NOTIFICATION_0012")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TOPIC")
    private String topic;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DATE")
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name = "SENDER_TYPE")
    private SenderType senderType;
    @Column(name = "SENDER")
    private String sender;
}