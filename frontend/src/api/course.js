import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8082/api/v1/courses',
    headers: {
        "content-type": "application/json",
    }
})

axiosInstance.interceptors.request.use((config) => {
    const token = JSON.parse(localStorage.getItem("token"))
    if(token) {
        config.headers.Authorization = `${token}`
    }

    return config
})

const courseAPI = {
    async getCourses(token, isTeacher, id) {
        if(isTeacher) return axiosInstance.get(`/teachers/${id}`, {})
        return axiosInstance.get(`/students/${id}`, {})
    },
    async addCourse(token, req) {
        return axiosInstance.post("/", {...req}, {})
    }
}

export default courseAPI;