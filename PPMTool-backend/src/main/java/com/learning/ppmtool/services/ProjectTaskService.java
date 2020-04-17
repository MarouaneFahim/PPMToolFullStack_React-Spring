package com.learning.ppmtool.services;

import com.learning.ppmtool.exeptions.ProjectNotFoundException;
import com.learning.ppmtool.models.Backlog;
import com.learning.ppmtool.models.Project;
import com.learning.ppmtool.models.ProjectTask;
import com.learning.ppmtool.repositories.BacklogRepository;
import com.learning.ppmtool.repositories.ProjectRepository;
import com.learning.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            Integer BacklogSequence = backlog.getPTSequence();
            BacklogSequence += 1;

            backlog.setPTSequence(BacklogSequence);
            projectTask.setBacklog(backlog);
            projectTask.setProjectIdentifier(projectIdentifier);
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);

            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            if (projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project with ID '"+projectIdentifier+"' does not exist");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id){
        Project project= projectRepository.findByProjectIdentifier(id);
        if (project == null){
            throw new ProjectNotFoundException("Project with ID '"+id+"' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findByPTSequence(String backlog_id, String pt_sq){
        Project project=projectRepository.findByProjectIdentifier(backlog_id);
        if (project == null){
            throw new ProjectNotFoundException("Project with ID '"+backlog_id+"' does not exist");
        }

        ProjectTask projectTask= projectTaskRepository.findByProjectSequence(pt_sq);
        if (projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+pt_sq+"' does not exist");
        }

        if (!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+pt_sq+"' does not exist in Project '"+backlog_id+"'");
        }
        return projectTask;
    }
}
