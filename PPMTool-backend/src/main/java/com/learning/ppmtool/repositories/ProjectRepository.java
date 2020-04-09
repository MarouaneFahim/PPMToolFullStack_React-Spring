package com.learning.ppmtool.repositories;

import com.learning.ppmtool.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project , Long> {
    Project findByProjectIdentifier(String projectId);
}
