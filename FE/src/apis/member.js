import { NadoriAxios } from "@/utils/axios";

const MemberAPI= NadoriAxios()

const login = (code, success, fail) => {
    MemberAPI.get(`/api/member/login/kakao?code=${code}`)
    .then(success).catch(fail)
}

export default {login}