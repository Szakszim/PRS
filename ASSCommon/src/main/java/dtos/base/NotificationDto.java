package dtos.base;

import entities.*;
import enums.SenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;
    private String topic;
    private String message;
    private Date date;
    private SenderType senderType;
    private String sender;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.topic = notification.getTopic();
        this.message = notification.getMessage();
        this.date = notification.getDate();
        this.senderType = notification.getSenderType();
        this.sender = notification.getSender();
    }

    public Notification toEntity() {
        Notification notification = new Notification();
        notification.setId(this.id);
        notification.setTopic(this.topic);
        notification.setMessage(this.message);
        notification.setDate(this.date);
        notification.setSenderType(this.senderType);
        notification.setSender(this.sender);
        return notification;
    }
}
