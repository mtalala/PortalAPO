package br.project.portalapo.service;

import br.project.portalapo.model.ActivityItem;
import br.project.portalapo.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityItem> findAll() {
        return activityRepository.findAll();
    }

    public ActivityItem findById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity não encontrada"));
    }

    public ActivityItem create(ActivityItem activity) {
        return activityRepository.save(activity);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }
}