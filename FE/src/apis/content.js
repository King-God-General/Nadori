import { NadoriAxios } from "@/utils/axios";

const contentAPI= NadoriAxios()

const getContents = (planId, success, fail) => {
    contentAPI.get(`/api/plan/contents/${planId}`)
    .then(success).catch(fail)
}
const postContents = (planId, contents, success, fail) => {
    contentAPI.post(`/api/plan/contents/${planId}`, contents)
    .then(success).catch(fail)
}
const deleteContents = (planId, success, fail) => {
    contentAPI.delete(`/api/plan/contents/${planId}`)
    .then(success).catch(fail)
}
const putContents = (planId, contents, success, fail) => {
    contentAPI.put(`/api/plan/contents/${planId}`, contents)
    .then(success).catch(fail)
}

export default {getContents, deleteContents,postContents, putContents}