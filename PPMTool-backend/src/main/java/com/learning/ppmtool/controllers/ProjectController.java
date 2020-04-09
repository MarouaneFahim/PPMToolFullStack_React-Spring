package com.learning.ppmtool.controllers;

import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<Project> creatNewProject(@RequestBody Project project){
        Project p= projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(p , HttpStatus.CREATED);
    }
}
