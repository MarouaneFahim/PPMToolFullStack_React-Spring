package com.learning.ppmtool.services;

import com.learning.ppmtool.exeptions.ProjectIdExeption;
import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdExeption("Project Id '"+ project.getProjectIdentifier().toUpperCase() +"' already exists");
        }
    }
}
