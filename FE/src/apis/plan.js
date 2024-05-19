import { NadoriAxios } from "@/utils/axios";

const planAPI= NadoriAxios()

const getPlans = (success, fail) => {
    planAPI.get('api/plan').then(success).catch(fail)
}
const getPlansByKeyword = (keyword,success, fail) => {
    planAPI.get('api/plan?keyword='+keyword).then(success).catch(fail)
}
const getPlansByUsername = (username,success, fail) => {
    planAPI.get('api/plan?username='+username).then(success).catch(fail)
}
const postPlan = (plan, success, fail) => {
    planAPI.post('api/plan', plan).then(success).catch(fail)
}
const putPlan = (plan, success, fail) => {
    planAPI.put('api/plan', plan).then(success).catch(fail)
}
const deletePlan = (){

}

export default {getPlans, getPlansByKeyword, getPlansByUsername, postPlan}