package com.learning.ppmtool.controllers;

import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.services.ProjectService;
import com.learning.ppmtool.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
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
        return new ResponseEntity<Project>(p , HttpStatus.CREATED);
    }
}
