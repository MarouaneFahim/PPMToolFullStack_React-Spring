package com.learning.ppmtool.exeptions;

public class ProjectIdExeptionResponse {
    private String projectIdentifier;

    public ProjectIdExeptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
