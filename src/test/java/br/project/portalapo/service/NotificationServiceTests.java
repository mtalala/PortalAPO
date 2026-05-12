package br.project.portalapo.service;

import br.project.portalapo.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NotificationService Tests")
class NotificationServiceTests {

    private NotificationService notificationService;
    private List<Notification> mockNotifications;

    @BeforeEach
    void setUp() {
        mockNotifications = new ArrayList<>();
        
        Notification notification1 = new Notification(
            "1",
            "ADMIN",
            "Nova solicitação recebida",
            "blue",
            "2024-05-12",
            false
        );
        
        Notification notification2 = new Notification(
            "2",
            "USER",
            "Solicitação aprovada",
            "green",
            "2024-05-11",
            true
        );
        
        mockNotifications.add(notification1);
        mockNotifications.add(notification2);
        
        notificationService = new NotificationService(mockNotifications);
    }

    @Test
    @DisplayName("Should find all notifications")
    void testFindAll() {
        List<Notification> result = notificationService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should find unread notifications")
    void testFindUnread() {
        List<Notification> result = notificationService.findUnread();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertFalse(result.get(0).isRead());
    }

    @Test
    @DisplayName("Should return empty list if all read")
    void testFindUnreadEmpty() {
        List<Notification> allRead = new ArrayList<>();
        Notification notification = new Notification(
            "1",
            "ADMIN",
            "Test",
            "blue",
            "2024-05-12",
            true
        );
        allRead.add(notification);
        
        NotificationService service = new NotificationService(allRead);
        List<Notification> result = service.findUnread();

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Should have correct notification data")
    void testNotificationContent() {
        List<Notification> notifications = notificationService.findAll();
        Notification first = notifications.get(0);

        assertEquals("1", first.getId());
        assertEquals("ADMIN", first.getRole());
        assertEquals("Nova solicitação recebida", first.getTopic());
        assertEquals("blue", first.getRequestColor());
        assertEquals("2024-05-12", first.getCreatedAt());
        assertFalse(first.isRead());
    }
}
