import { GET_PROJECTS } from "../actions/Types";

const initalState = {
  projects: [],
  project: {}
};

export default function(state = initalState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload
      };

    default:
      return state;
  }
}
