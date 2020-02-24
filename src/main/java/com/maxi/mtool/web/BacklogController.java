package com.maxi.mtool.web;

import com.maxi.mtool.domain.ProjectTask;
import com.maxi.mtool.service.MapValidationErrorService;
import com.maxi.mtool.service.ProjectTaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

  private final ProjectTaskService projectTaskService;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public BacklogController(ProjectTaskService projectTaskService, MapValidationErrorService mapValidationErrorService) {
    this.projectTaskService = projectTaskService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
      BindingResult result, @PathVariable String backlog_id){
    ResponseEntity<?> errorMap = mapValidationErrorService.validationService(result);
    if (errorMap != null) return errorMap;

    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);
    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
  }

  @GetMapping("/{backlog_id}")
  public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
    return projectTaskService.findBacklogById(backlog_id);
  }
}
