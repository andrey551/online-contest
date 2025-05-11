import {logoutEvent, setLoading} from "./state";
import laboratoryAPI from "../../api/laboratory";
import resourcesAPI from "../../api/resource";
import testAPI from "../../api/test";
import solutionAPI from "../../api/solution";

const SET_LABORATORIES = 'SET_LABORATORIES'
const SET_DETAIL = 'SET_DETAIL'
const SET_SUCCESS = 'SET_SUCCESS'
const SET_LABORATORY_ABOUT = 'SET_LABORATORY_ABOUT'
const SET_LABORATORY_DESCRIPTION = 'SET_LABORATORY_DESCRIPTION'
const SET_LABORATORY_RESOURCE = 'SET_LABORATORY_RESOURCE'
const SET_LABORATORY_TEST_SET = 'SET_LABORATORY_TEST_SET'
const SET_LABORATORY_ADDED_ID = 'SET_LABORATORY_ADDED_ID'

const initialState = {
    laboratories: [],
    detail: null,
    laboratoryToAdd: {
        about: {
            title: '',
            tags: '',
            deadline: null
        },
        description: '',
        resource: [],
        testSet: []
    },
    laboratoryAddedId: '',
    success: true
}

export default function laboratoryReducer(state = initialState, action = {}) {
    switch(action.type) {
        case SET_LABORATORIES:
            return Object.assign(
                {},
                state,
                {
                    laboratories: action.value
                }
            )
        case SET_LABORATORY_DESCRIPTION:
            return Object.assign(
                {},
                state,
                {
                    laboratoryToAdd: {
                        ...state.laboratoryToAdd,
                        description: action.value
                    }
                }
            )
        case SET_LABORATORY_RESOURCE:
            console.log(action.value)
            return Object.assign(
                {},
                state,
                {
                    laboratoryToAdd: {
                        ...state.laboratoryToAdd,
                        resource: [...state.laboratoryToAdd.resource, action.value]
                    }
                }
            )
        case SET_LABORATORY_TEST_SET:
            return Object.assign(
                {},
                state,
                {
                    laboratoryToAdd: {
                        ...state.laboratoryToAdd,
                        testSet: [...state.laboratoryToAdd.testSet, action.value]
                    }
                }
            )
        case SET_DETAIL:
            return Object.assign(
                {},
                state,
                {
                    detail: action.value
                }
            )
        case SET_LABORATORY_ADDED_ID:
            return  Object.assign(
                {},
                state,
                {
                    laboratoryAddedId: action.value
                }
            )
        case SET_SUCCESS:
            return Object.assign(
                {},
                state,
                {
                    success: state.success && action.value
                }
            )
        case SET_LABORATORY_ABOUT:
            return Object.assign(
                {},
                state,
                {
                    laboratoryToAdd: {
                        ...state.laboratoryToAdd,
                        about: action.value
                    }
                }
            )
        default:
            return state;
    }
}

export function setLaboratories(value) {
    return {type: SET_LABORATORIES, value}
}

export function setDescription(value) {
    return {type: SET_LABORATORY_DESCRIPTION, value}
}

export function setResource(value) {
    return {type: SET_LABORATORY_RESOURCE, value}
}

export function setTestSet(value) {
    return {type: SET_LABORATORY_TEST_SET, value}
}

export function setDetail(value) {
    return {type: SET_DETAIL, value}
}

export function setSuccess(value) {
    return {type: SET_SUCCESS, value}
}

export function setAddedLaboratoryId(value) {
    return {type: SET_LABORATORY_ADDED_ID, value}
}

export function setLaboratoryAbout(value) {
    return {type: SET_LABORATORY_ABOUT, value}
}

export const getLaboratoriesEvent = () => (dispatch,getState) => {
    dispatch(setLoading(true))
    laboratoryAPI.getLaboratories(localStorage.getItem('token'), getState().crtState.crtCourse.id)
        .then(response => {
            if(response.status === 200) {
                dispatch(setLaboratories(response.data))
                console.log(response.data)
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

export const getDetailEvent = (req) => (dispatch) => {
    dispatch(setLoading(true));
    laboratoryAPI.getLaboratory(localStorage.getItem('token'), req)
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

export const addRDescriptionEvent = (req) => (dispatch, getState) => {
    dispatch(setLoading(true))
    laboratoryAPI.addLaboratory(localStorage.getItem('token'), req, getState().laboratoryToAdd.description)
        .then(response => {
            if(response.status === 200) {
                dispatch(setSuccess(true))
                dispatch(setAddedLaboratoryId(response.value))
            } else {
                dispatch(setSuccess(false))
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

export const addResourceEvent = () => (dispatch, getState) => {
    dispatch(setLoading(true))
    resourcesAPI.addResources(localStorage.getItem('token'), getState().laboratoryToAdd, getState.laboratoryToAdd.resource)
        .then(response => {
            if(response.status === 200) {
                dispatch(setSuccess(true))
            } else {
                dispatch(setSuccess(false))
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

export const addTestSetEvent = () => (dispatch, getState) => {
    dispatch(setLoading(true))
    testAPI.addTestSet(localStorage.getItem('token'), getState.laboratoryToAdd, getState.laboratoryToAdd.testSet)
        .then(response => {
            if(response.status === 200) {
                dispatch(setSuccess(true))
            } else {
                dispatch(setSuccess(false))
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

export const sendSolutionEvent = (solution) => (dispatch, getState) => {
    dispatch(setLoading(true));
    solutionAPI.addSolution(localStorage.getItem('token'),
                            getState().crtState.crtLaboratory.id,
                            getState().user.user.nickname,
                            solution)
        .then()
}

