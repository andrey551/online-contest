const SET_LOADING = 'SET_LOADING'
const SET_CURRENT_COURSE = 'SET_CURRENT_COURSE'
const SET_CURRENT_LABORATORY = 'SET_CURRENT_LABORATORY'
const SET_LOG_OUT = 'SET_LOG_OUT'

const initialState = {
    isLoading: false,
    crtCourse: null,
    crtLaboratory: null,
    isLoggedOut: true
};

export default function stateReducer(state = initialState, action = {}) {
    switch(action.type) {
        case SET_LOADING:
            return Object.assign(
                {},
                state,
                {
                    isLoading: action.value
                }
            )
        case SET_CURRENT_COURSE:
            return Object.assign(
                {},
                state,
                {
                    crtCourse: action.value
                }
            )
        case SET_CURRENT_LABORATORY:
            return Object.assign(
                {},
                state,
                {
                    crtLaboratory: action.value
                }
            )
        case SET_LOG_OUT:
            return Object.assign(
                {},
                state,
                {
                    isLoggedOut: action.value
                }
            )
        default:
            return state;
    }
}

export function setLoading(value) {
    return {type: SET_LOADING, value}
}

export function setCurrentCourse(value) {
    return {type: SET_CURRENT_COURSE, value}
}

export function setCurrentLaboratory(value) {
    return {type: SET_CURRENT_LABORATORY, value}
}

export function setLogOut(value) {
    return {type: SET_LOG_OUT, value}
}

export const logoutEvent = () => (dispatch) => {
    dispatch(setLogOut(true));
    localStorage.removeItem('token');
}



