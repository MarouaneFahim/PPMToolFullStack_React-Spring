package com.learning.ppmtool.controllers;

import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.services.ProjectService;
import com.learning.ppmtool.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin("*")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> creatNewProject(@Valid @RequestBody Project project , BindingResult result){
        ResponseEntity<?> errorMap= validationService.MapValidation(result);
        if (errorMap != null) return errorMap;

        Project p= projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(p , HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project= projectService.finProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<>(project , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProjects(){
        return new ResponseEntity<>(projectService.findAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<>("Project with ID '"+projectId.toUpperCase()+"' was deleted" , HttpStatus.OK);
    }
}
