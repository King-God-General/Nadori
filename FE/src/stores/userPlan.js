import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useUserPlanStore = defineStore('userPlan', () => {
    const orgPlan = ref()
    const curPlan = ref()
    const curDayNum = ref(1)
    return {orgPlan, curPlan, curDayNum}
})

