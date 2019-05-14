package dtos.base;

import entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTargetDto {
    private Long id;
    private Notification notification;
    private Account recipient;
    private Farm farm;
    private Boolean readingIndicator;

    public NotificationTargetDto(NotificationTarget notificationTarget) {
        this.id = notificationTarget.getId();
        this.notification = notificationTarget.getNotification();
        this.recipient = notificationTarget.getRecipient();
        this.farm = notificationTarget.getFarm();
        this.readingIndicator = notificationTarget.getReadingIndicator();
    }

    public NotificationTarget toEntity() {
        NotificationTarget notificationTarget = new NotificationTarget();
        notificationTarget.setId(this.id);
        notificationTarget.setNotification(this.notification);
        notificationTarget.setRecipient(this.recipient);
        notificationTarget.setFarm(this.farm);
        notificationTarget.setReadingIndicator(this.readingIndicator);
        return notificationTarget;
    }
}
