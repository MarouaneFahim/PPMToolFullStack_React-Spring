package com.learning.ppmtool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence= 0;
    private String projectIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true, mappedBy = "backlog")
    private List<ProjectTask> projectTasks= new ArrayList<>();

}
