package com.learning.ppmtool.services;

import com.learning.ppmtool.models.Backlog;
import com.learning.ppmtool.models.ProjectTask;
import com.learning.ppmtool.repositories.BacklogRepository;
import com.learning.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        Backlog backlog= backlogRepository.findByProjectIdentifier(projectIdentifier);
        Integer BacklogSequence= backlog.getPTSequence();
        BacklogSequence += 1;
        backlog.setPTSequence(BacklogSequence);

        projectTask.setBacklog(backlog);
        projectTask.setProjectIdentifier(projectIdentifier);
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);

        if (projectTask.getPriority()== null){
            projectTask.setPriority(3);
        }
        if (projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String id){
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

}
