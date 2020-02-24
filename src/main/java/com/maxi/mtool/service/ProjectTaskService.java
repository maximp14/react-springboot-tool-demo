package com.maxi.mtool.service;

import com.maxi.mtool.domain.Backlog;
import com.maxi.mtool.domain.ProjectTask;
import com.maxi.mtool.repository.BacklogRepository;
import com.maxi.mtool.repository.ProjectTaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

  private final BacklogRepository backlogRepository;
  private final ProjectTaskRepository projectTaskRepository;

  @Autowired
  public ProjectTaskService(BacklogRepository backlogRepository,
      ProjectTaskRepository projectTaskRepository) {
    this.backlogRepository = backlogRepository;
    this.projectTaskRepository = projectTaskRepository;
  }

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
    Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
    projectTask.setBacklog(backlog);
    Integer BacklogSequence = backlog.getPTSequence();
    BacklogSequence++;
    backlog.setPTSequence(BacklogSequence);
    projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);
    if (projectTask.getPriority() == null){
      projectTask.setPriority(3);
    }
    if (projectTask.getStatus() == "" || projectTask.getStatus() == null){
      projectTask.setStatus("TODO");
    }
    return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask>findBacklogById(String id){
    return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
  }
}
