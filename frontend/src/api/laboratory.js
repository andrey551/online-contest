import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8082/api/v1/laboratories',
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

const laboratoryAPI = {
    async getLaboratories(token, courseId) {
      return axiosInstance.get(`course/${courseId}`)
    },
    async getLaboratory(token) {
        return axiosInstance.get('/')
    },
    async addLaboratory(token, id, req) {
        return axiosInstance.post(`/${id}`, req)
    }
}

export default laboratoryAPI;