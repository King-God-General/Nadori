import {ref, reactive} from 'vue'
import {defineStore} from 'pinia'

export const useNadoriStore = defineStore('nadori', () => {
    const member = reactive({
        id: 0,
        username: ''
    });
    const planDetail = ref()
    const curDayNum = ref(1)
    return {member, planDetail, curDayNum}
})