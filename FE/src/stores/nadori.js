import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useNadoriStore = defineStore('nadori', () => {
    const member = ref(null);
    const plan = ref();
    const planDetail = ref();
    const curDayNum = ref(1);
    return {member, plan, planDetail, curDayNum}
})