package com.learning.ppmtool.exeptions;

public class ProjectNotFoundExceptionResponse {
    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
    public String getProjectNotFound() {
        return projectNotFound;
    }
    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
