import { combineReducers } from "redux";
import errorReducer from "./ErrorReducer";

export default combineReducers({
  erros: errorReducer
});
