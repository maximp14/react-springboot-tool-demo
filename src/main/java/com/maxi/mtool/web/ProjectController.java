package com.maxi.mtool.web;

import com.maxi.mtool.domain.Project;
import com.maxi.mtool.service.MapValidationErrorService;
import com.maxi.mtool.service.ProjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
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
}
