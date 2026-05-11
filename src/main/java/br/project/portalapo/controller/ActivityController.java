package br.project.portalapo.controller;

import br.project.portalapo.model.ActivityItem;
import br.project.portalapo.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityItem> getAll() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public ActivityItem getById(@PathVariable Long id) {
        return activityService.findById(id);
    }

    @PostMapping
    public ActivityItem create(@RequestBody ActivityItem activity) {
        return activityService.create(activity);
    }

    @PutMapping("/{id}")
    public ActivityItem update(
            @PathVariable Long id,
            @RequestBody ActivityItem activity
    ) {
        return activityService.update(id, activity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}