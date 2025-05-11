import axios from "axios";
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8084/api/v1/solution',
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

const solutionAPI = {
    async addSolution(token, laboratory_id, author_id, file) {
        const formData = new FormData();
        formData.append("laboratory_id", laboratory_id);
        formData.append("author_id", author_id);
        formData.append("file", file); // file must be a File or Blob object

        return axiosInstance.post('', formData, {
            headers: {
                'Authorization': `Bearer ${token}`, // optional, if needed
                'Content-Type': 'multipart/form-data'
            }
        });
    }
}

export default solutionAPI;