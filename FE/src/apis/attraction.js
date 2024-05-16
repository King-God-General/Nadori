import { attractionAxios } from "@/utils/axios";

const attractionAPI= attractionAxios()

//api 호출 함수
const getAllData = (success,fail) => {
    attractionAPI.get('api/Attraction').then(success).catch(fail)
}

const getConditionalData = (sidoCode, gugunCode, contentTypeId, success,fail) => {
    attractionAPI.get('api/Attraction/'+sidoCode+'/'+gugunCode+'/'+contentTypeId).then(success).catch(fail)
}

export default {getAllData, getConditionalData}