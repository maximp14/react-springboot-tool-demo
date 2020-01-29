package com.maxi.mtool.web;

import com.maxi.mtool.domain.Project;
import com.maxi.mtool.service.MapValidationErrorService;
import com.maxi.mtool.service.ProjectService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
@CrossOrigin("*")
public class ProjectController {

  private final ProjectService projectService;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
    this.projectService = projectService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
    ResponseEntity<?> errorMap = mapValidationErrorService.validationService(result);
    if (errorMap != null)return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable("projectId") String projectId){
    Project projectByIdentifier = projectService.findProjectByIdentifier(projectId);

    return new ResponseEntity<Project>(projectByIdentifier, HttpStatus.OK);
  }

  @GetMapping("/projects")
  public Iterable<Project> getAllProjects(){
    return projectService.findAllProjects();
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> deleteProject(@PathVariable("projectId") String projectId){
    projectService.deleteProjectByIdentifier(projectId);
    return new ResponseEntity<String>("Project deleted", HttpStatus.OK);
  }
}
