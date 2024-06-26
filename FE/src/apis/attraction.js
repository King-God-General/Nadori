import { NadoriAxios } from "@/utils/axios";

const attractionAPI= NadoriAxios()

const getAllData = (success,fail) => {
    attractionAPI.get('api/attraction').then(success).catch(fail)
}
const getConditionalData = (sidoCode, gugunCode, contentTypeId, success,fail) => {
    attractionAPI.get('api/attraction/'+sidoCode+'/'+gugunCode+'/'+contentTypeId).then(success).catch(fail)
}

export default {getAllData, getConditionalData}