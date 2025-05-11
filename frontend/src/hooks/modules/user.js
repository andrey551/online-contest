import userAPI from "../../api/user";
import {logoutEvent, setLoading} from "./state";
const SET_USER = 'SET_USER';
const SET_IS_TEACHER = 'SET_IS_TEACHER';

const initialState = {
    user: {
        id: 'a9c24e8e-57db-4a82-a5e5-81970c5db7a2',
        nickname:'',
        fullname: '',
        organization: '',
        email: ''
    },
    isTeacher: true
}

export default function userReducer(state = initialState, action = {}) {
    switch(action.type) {
        case SET_USER:
            return Object.assign(
                {},
                state,
                {
                    user: action.value
                }
            )
        case SET_IS_TEACHER:
            return Object.assign(
                {},
                state,
                {
                    isTeacher: action.value
                }
            )
        default:
            return state;
    }
}

export function setUser(value) {
    return {type: SET_USER, value}
}

export function setTeacher(value) {
    return {type: SET_IS_TEACHER, value}
}

export const getUser = () =>(dispatch) => {
    dispatch(setLoading(true))
    userAPI.getUser(JSON.parse(localStorage.getItem('token')))
        .then(response => {
            if(response.status === 200) {
                dispatch(setUser(response.data))
            } else {
                console.log(`unexpected response ${response.status} from server`);
            }
        })
        .catch(err => {
            if(err.status === 401) {
                dispatch(logoutEvent());
            } else {
                console.log(`unexpected response ${err.status} from server`)
            }
        })
    dispatch(setLoading(false));
}

export const checkIsTeacher = () => (dispatch) => {
    dispatch(setLoading(true))
    // todo : call API to check if token is belong to teacher
    dispatch(setTeacher(true))
    dispatch(setLoading(false));
}
