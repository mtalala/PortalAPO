package br.project.portalapo.controller;

import br.project.portalapo.model.Notification;
import br.project.portalapo.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("NotificationController Tests")
class NotificationControllerTests {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    @DisplayName("Should get all notifications")
    void testGetAll() throws Exception {
        Notification notification1 = new Notification(
            "1",
            "ADMIN",
            "Nova solicitação",
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

        List<Notification> notifications = Arrays.asList(notification1, notification2);
        when(notificationService.findAll()).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(notificationService, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no notifications")
    void testGetAllEmpty() throws Exception {
        when(notificationService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(notificationService, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return correct notification data")
    void testGetAllContent() throws Exception {
        Notification notification = new Notification(
            "1",
            "ADMIN",
            "Nova solicitação",
            "blue",
            "2024-05-12",
            false
        );

        List<Notification> notifications = Arrays.asList(notification);
        when(notificationService.findAll()).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].role").value("ADMIN"))
                .andExpect(jsonPath("$[0].topic").value("Nova solicitação"))
                .andExpect(jsonPath("$[0].requestColor").value("blue"))
                .andExpect(jsonPath("$[0].read").value(false));

        verify(notificationService, times(1)).findAll();
    }
}
