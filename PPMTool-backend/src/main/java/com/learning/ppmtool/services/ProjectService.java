package com.learning.ppmtool.services;

import com.learning.ppmtool.exeptions.ProjectIdExeption;
import com.learning.ppmtool.models.Backlog;
import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.repositories.BacklogRepository;
import com.learning.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            String identifier= project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(identifier);

            if (project.getId()== null){
                Backlog backlog= new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }
            if(project.getId()!= null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdExeption("Project Id '"+ project.getProjectIdentifier().toUpperCase() +"' already exists");
        }
    }

    public Project finProjectByIdentifier(String projectId){
        Project project= projectRepository.findByProjectIdentifier(projectId);
        if (project == null){
            throw new ProjectIdExeption("Project Id '"+ projectId +"' doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project= projectRepository.findByProjectIdentifier(projectId);
        if (project == null){
            throw new ProjectIdExeption("canoot delete Project with ID '"+projectId+"'. this project doesn't exist");
        }
        projectRepository.delete(project);
    }
}
