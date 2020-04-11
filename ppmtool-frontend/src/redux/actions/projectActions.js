import axios from "axios";
import { GET_ERRORS } from "./types";

export const creatProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8088/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
