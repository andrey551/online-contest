import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/user',
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
const userAPI = {
    async getUser(token) {
        return axiosInstance.get('/', {})
    },
}

export default userAPI;