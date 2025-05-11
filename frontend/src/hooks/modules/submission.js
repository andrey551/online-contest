import {logoutEvent, setLoading} from "./state";
import submissionAPI from "../../api/submission";
import solutionAPI from "../../api/solution";
import testAPI from "../../api/test";

const SET_SUBMISSIONS = 'SET_SUBMISSIONS'
const SET_DETAIL = 'SET_DETAIL'
const SET_SOLUTION = 'SET_SOLUTION'
const SET_SUCCESS = 'SET_SUCCESS'

const initialState = {
    submissions: [],
    submissionDetail: null,
    solution: null,
    success: false
}

export default function submissionReducer(state = initialState, action = {}) {
    switch(action.type) {
        case SET_SUBMISSIONS:
            return Object.assign(
                {},
                state,
                {
                    submissions: action.value
                }
            )
        case SET_SOLUTION:
            return Object.assign(
                {},
                state,
                {
                    solution: action.value
                }
            )
        case SET_SUCCESS:
            return Object.assign(
                {},
                state,
                {
                    success: action.value
                }
            )
        case SET_DETAIL:
            return Object.assign(
                {},
                state,
                {
                    submissionDetail: action.value
                }
            )
        default:
            return state
    }
}

export function setSubmissions(value) {
    return {type: SET_SUBMISSIONS, value}
}

export function setSolution(value) {
    return {type: SET_SOLUTION, value}
}

export function setSuccess(value) {
    return {type: SET_SUCCESS, value}
}

export function setDetail(value) {
    return {type: SET_DETAIL, value}
}

export const getSubmissionsEvent = () => (dispatch, getState) => {
    dispatch(setLoading(true))
    submissionAPI.getSubmissions(
        localStorage.getItem('token'),
        getState().user.user.id,
        getState().crtState.crtLaboratory.id)
        .then(response => {
            if(response.status === 200) {
                dispatch(setSubmissions(response.data))
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

export const getDetailEvent = (id) => (dispatch) => {
    dispatch(setLoading(true))
    submissionAPI.getDetail(
        localStorage.getItem('token'),
        id)
        .then(response => {
            if(response.status === 200) {
                dispatch(setDetail(response.data))
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

export const sendSolutionEvent = () => (dispatch, getState) => {
    dispatch(setLoading(true))
    solutionAPI.addSolution(
        localStorage.getItem('token'),
        getState().crtState.crtLaboratory.id,
        getState().user.user.id,
        getState().submission.solution
    ).then(response => {
        if(response.status === 200) {
            console.log(response.data)
            dispatch(setSuccess(true));
            dispatch(startTestEvent(
                response.data.solution_id,
                response.data.submission_id,
                getState().crtState.crtLaboratory.id)
            )
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

export const startTestEvent = (solutionId, submissionId, laboratoryId) => (dispatch) => {
    dispatch(setLoading(true))
    testAPI.runTest(localStorage.getItem('token'), {
        laboratory_id: laboratoryId,
        solution_id: solutionId,
        submission_id: submissionId
    })
        .then(response => {
            if (response.status === 200) {
                console.log("Tested successfully!")
                dispatch(getSubmissionsEvent());
            } else {
                console.log(`unexpected error ${response.status} from server`)
            }
        })
        .catch(err => {
            console.log(err)
            if (err.status === 401) {
                dispatch(logoutEvent());
            } else {
                console.log(`unexpected error ${err.status} from server`)
            }
        })
}