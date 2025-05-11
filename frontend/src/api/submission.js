import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8083/api/v1/submissions',
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

const submissionAPI = {
    async getSubmissions(token, studentId, laboratoryId) {
        return axiosInstance.get(`/student/${studentId}/laboratory/${laboratoryId}`)
    },
    async getDetail(token, id) {
        return axiosInstance.get(`detail/${id}`)
    }
}

export default submissionAPI;