package com.learning.ppmtool.controllers;

import com.learning.ppmtool.models.ProjectTask;
import com.learning.ppmtool.services.ProjectTaskService;
import com.learning.ppmtool.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin("*")
public class BacklogController {
    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidationService validationService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id){
        ResponseEntity<?> errorMap= validationService.MapValidation(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1= projectTaskService.addProjectTask(backlog_id.toUpperCase(), projectTask);
        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public ResponseEntity<?> getBacklogById(@PathVariable String backlog_id){
        return new ResponseEntity<>(projectTaskService.findBacklogById(backlog_id.toUpperCase()), HttpStatus.OK);
    }

    @GetMapping("/{backlog_id}/{pt_sq}")
    public ResponseEntity<?> getProjectTaskByPTSequence(@PathVariable String backlog_id, @PathVariable String pt_sq){
        return new ResponseEntity<>(projectTaskService.findByPTSequence(backlog_id.toUpperCase(), pt_sq.toUpperCase()),
                                    HttpStatus.OK);
    }
}
