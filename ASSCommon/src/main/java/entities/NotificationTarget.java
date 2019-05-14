package entities;

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
@Table(name = "GEN_NOTIFICATION_TARGET_0013")
public class NotificationTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "NOTIFICATION")
    private Notification notification;
    @ManyToOne()
    @JoinColumn(name = "RECIPIENT")
    private Account recipient;
    @ManyToOne()
    @JoinColumn(name = "FARM")
    private Farm farm;
    @Column(name = "READING_INDICATOR")
    private Boolean readingIndicator;
}