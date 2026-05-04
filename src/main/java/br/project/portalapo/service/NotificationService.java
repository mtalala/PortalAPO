package br.project.portalapo.service;

import br.project.portalapo.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final List<Notification> notifications;

    public NotificationService(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Notification> findAll() {
        return notifications;
    }

    public List<Notification> findUnread() {
        return notifications.stream()
                .filter(n -> !n.isRead())
                .toList();
    }
}