import { combineReducers } from "redux";
import errorReducer from "./ErrorReducer";
import ProjectReducer from "./ProjectReducer";

export default combineReducers({
  errors: errorReducer,
  project: ProjectReducer
});
