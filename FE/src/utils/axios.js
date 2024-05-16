import axios from "axios";

const {VITE_API_BASE_URL} = import.meta.env
const attractionAxios = () => {

const instance = axios.create({
    baseURL : VITE_API_BASE_URL,
    headers: {
        'Content-Type': 'appliation/json; charset=utf-8;'
    }
})
return instance
}

export {attractionAxios};