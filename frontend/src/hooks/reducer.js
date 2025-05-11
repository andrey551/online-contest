import { combineReducers } from "redux";
import stateReducer from "./modules/state";
import userReducer from "./modules/user";
import courseReducer from "./modules/course";
import laboratoryReducer from "./modules/laboratory";
import submissionReducer from "./modules/submission";

const reducer = combineReducers(
    {
        crtState: stateReducer,
        user: userReducer,
        course: courseReducer,
        laboratory: laboratoryReducer,
        submission: submissionReducer
    }
)

export default reducer;