package com.maxi.mtool.service;

import com.maxi.mtool.domain.Project;
import com.maxi.mtool.exception.ProjectIdException;
import com.maxi.mtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
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

}
