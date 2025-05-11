import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/resources',
    headers: {
        "content-type": "application/json",
    }
})

axiosInstance.interceptors.request.use((config) => {
    const token = JSON.parse(localStorage.getItem("tad"))
    if(token) {
        config.headers.Authorization = `${token}`
    }

    return config
})

const resourcesAPI = {
    async getResources(token, laboratoryId) {
        return axiosInstance.get(`resources/${laboratoryId}`)
    },
    async addResources(token, id, data) {
        return axiosInstance.post(`/${id}`, data)
    },
}

export default resourcesAPI;