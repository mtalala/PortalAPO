package br.project.portalapo.repository;

import br.project.portalapo.model.ActivityItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityItem, Long> {
}