import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useUserPlanStore = defineStore('userPlan', () => {
    const orgPlan = ref({
        1: { plan: [] },
        2: { plan: [] },
        3: { plan: [] }
    })
    const curPlan = ref({
        1: { plan: [] },
        2: { plan: [] },
        3: { plan: [] }
    })
    const curDayNum = ref(1)
    return {orgPlan, curPlan, curDayNum}
})

