import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8085/api/v1/test-set',
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

const testAPI = {
    async getTestSet(token, laboratoryId) {
        return axiosInstance.get(`/${laboratoryId}`)
    },
    async addTestSet(token, req, test) {
        return axiosInstance.post(`/${req}`, test)
    },
    async runTest(token, req) {
        return axiosInstance.post('/run', req)
    }
}

export default testAPI;