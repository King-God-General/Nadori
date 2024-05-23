import { NadoriAxios } from "@/utils/axios";

const MemberAPI= NadoriAxios()

const loginOrSignUp = (code, success, fail) => {
    MemberAPI.get(`/api/member/login/kakao?code=${code}`)
    .then(success).catch(fail)
}
const logout = (memberId, success, fail) => {
    MemberAPI.get(`/api/member/logout/kakao/${memberId}`)
    .then(success).catch(fail)
}
const withdraw = (memberId, success, fail) => {
    MemberAPI.delete(`/withdraw/kakao/${memberId}`)
    .then(success).catch(fail)
}
const updateMemberInfo = (member, success, fail) => {
    MemberAPI.put(`/api/member/update/kakao`, member)
    .then(success).catch(fail)
}

export default {loginOrSignUp, logout, withdraw, updateMemberInfo}