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

    public Project finProjectByIdentifier(String projectId){
        Project project= projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdExeption("Project Id '"+ projectId.toUpperCase() +"' doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project= projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdExeption("canoot delete Project with ID '"+projectId.toUpperCase()+"'. this project doesn't exist");
        }
        projectRepository.delete(project);
    }
}
