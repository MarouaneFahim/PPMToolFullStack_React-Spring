import React, { Component } from "react";
import { Link } from "react-router-dom";

class ProjectTaskItem extends Component {
  render() {
    const { project_task } = this.props;

    let priorityString;
    let priorityClass;

    switch (project_task.priority) {
      case 1:
        priorityString = "HIGH";
        priorityClass = "bg-danger text-light";
        break;
      case 2:
        priorityString = "MEDIUM";
        priorityClass = "bg-warning text-light";
        break;
      case 3:
        priorityString = "LOW";
        priorityClass = "bg-info text-light";
        break;
      default:
        break;
    }
    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {project_task.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{project_task.summary}</h5>
          <p className="card-text text-truncate ">
            {project_task.acceptanceCriteria}
          </p>
          <Link to="" className="btn btn-primary">
            View / Update
          </Link>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}

export default ProjectTaskItem;