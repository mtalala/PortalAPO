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
    public ResponseEntity<List<ActivityItem>> getAll() {
        return ResponseEntity.ok(activityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityItem> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ActivityItem> create(@RequestBody ActivityItem activity) {
        return ResponseEntity.ok(activityService.create(activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}