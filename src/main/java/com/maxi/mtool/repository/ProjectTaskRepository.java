package com.maxi.mtool.repository;

import com.maxi.mtool.domain.ProjectTask;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
  List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
}
