import {logoutEvent, setLoading} from "./state";
import courseAPI from "../../api/course";
import {useSelector} from "react-redux";

const SET_COURSES = 'SET_COURSES'
const SET_COURSE_TO_ADD = 'SET_COURSE_TO_ADD'

const initialState = {
    courses: [],
    courseToAdd: null
}

export default function courseReducer(state = initialState, action = {}) {
    switch(action.type) {
        case SET_COURSES:
            return Object.assign(
                {},
                state,
                {
                    courses: action.value
                }
            )
        case SET_COURSE_TO_ADD:
            return Object.assign(
                {},
                state,
                {
                    courseToAdd: action.value
                }
            )
        default:
            return state;
    }
}

export function setCourses(value) {
    return {type:SET_COURSES, value}
}

export function setCourseToAdd(value) {
    return {type: SET_COURSE_TO_ADD, value}
}

export const getCoursesEvent = () => (dispatch, getState) => {
    dispatch(setLoading(true));
    courseAPI.getCourses(localStorage.getItem('token'), getState().user.isTeacher, getState().user.user.id)
        .then(response => {
            if(response.status === 200) {
                dispatch(setCourses(response.data))
                // if( response.data)
            } else {
                console.log(`unexpected response ${response.status} from server`)
            }
        })
        .catch(err => {
            console.log(err)
            if(err.status === 401) {
                dispatch(logoutEvent());
            } else {
                console.log(`unexpected error ${err.status} from server`)
            }
        })
    dispatch(setLoading(false))
}

export const addCourseEvent = () => (dispatch, getState) => {
    dispatch(setLoading(false))
    courseAPI.addCourse(localStorage.getItem('token'), getState().courseToAdd)
        .then(response => {
            if(response.status === 200) {
                dispatch(getCoursesEvent())
            } else {
                alert(`Unexpected response ${response.status} from server`)
            }
        })
        .catch(err => {
            if(err.response.status === 401) {
                dispatch(logoutEvent())
            } else {
                alert(`Unexpected response ${err.status} from server`)
            }
        })
    dispatch(setLoading(false))
}
