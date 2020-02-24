package com.maxi.mtool.service;

import com.maxi.mtool.domain.Backlog;
import com.maxi.mtool.domain.Project;
import com.maxi.mtool.exception.ProjectIdException;
import com.maxi.mtool.repository.BacklogRepository;
import com.maxi.mtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final BacklogRepository backlogRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
    this.projectRepository = projectRepository;
    this.backlogRepository = backlogRepository;
  }

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

      if (project.getId()==null){
        Backlog backlog = new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      }

      if (project.getId()!=null){
        project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
      }

      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException(
          "Project ID '" + project.getProjectIdentifier().toUpperCase() + "'already exists");
    }
  }

  public Project findProjectByIdentifier(String projectId) {

    Project byProjectIdentifier = projectRepository
        .findByProjectIdentifier(projectId.toUpperCase());
    if (byProjectIdentifier == null) {
      throw new ProjectIdException(
          "Project ID '" + projectId.toUpperCase()
              + "' does not exists");
    }
    return byProjectIdentifier;
  }

  public Iterable<Project> findAllProjects(){
    return projectRepository.findAll();
  }

  public void deleteProjectByIdentifier(String projectId){
    Project byProjectIdentifier = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

    if (byProjectIdentifier == null){
      throw new ProjectIdException(projectId + "not found");
    }

    projectRepository.delete(byProjectIdentifier);
  }

}
