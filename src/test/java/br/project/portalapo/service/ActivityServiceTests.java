package br.project.portalapo.service;

import br.project.portalapo.model.ActivityItem;
import br.project.portalapo.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ActivityService Tests")
class ActivityServiceTests {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    private ActivityItem activity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        activity = new ActivityItem();
        activity.setId(1L);
        activity.setLabel("Atividade de pesquisa");
        activity.setPoints(10);
    }

    @Test
    @DisplayName("Should get all activities")
    void testGetAllActivities() {
        ActivityItem activity2 = new ActivityItem();
        activity2.setId(2L);
        activity2.setLabel("Outra atividade");
        activity2.setPoints(5);

        List<ActivityItem> activities = Arrays.asList(activity, activity2);
        when(activityRepository.findAll()).thenReturn(activities);

        List<ActivityItem> result = activityService.findAll();

        assertEquals(2, result.size());
        verify(activityRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should get activity by id")
    void testGetActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));

        ActivityItem result = activityService.findById(1L);

        assertNotNull(result);
        assertEquals("Atividade de pesquisa", result.getLabel());
        verify(activityRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should create activity")
    void testCreateActivity() {
        when(activityRepository.save(any(ActivityItem.class))).thenReturn(activity);

        ActivityItem result = activityService.create(activity);

        assertNotNull(result);
        assertEquals("Atividade de pesquisa", result.getLabel());
        verify(activityRepository, times(1)).save(any(ActivityItem.class));
    }

    @Test
    @DisplayName("Should update activity")
    void testUpdateActivity() {
        ActivityItem updateData = new ActivityItem();
        updateData.setLabel("Atividade atualizada");
        updateData.setPoints(20);

        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));
        when(activityRepository.save(any(ActivityItem.class))).thenReturn(activity);

        ActivityItem result = activityService.update(1L, updateData);

        assertNotNull(result);
        verify(activityRepository, times(1)).findById(1L);
        verify(activityRepository, times(1)).save(any(ActivityItem.class));
    }

    @Test
    @DisplayName("Should delete activity")
    void testDeleteActivity() {
        activityService.delete(1L);

        verify(activityRepository, times(1)).deleteById(1L);
    }
}