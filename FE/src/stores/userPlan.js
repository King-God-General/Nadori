import {ref, computed} from 'vue'
import {defineStore} from 'pinia'

export const useUserPlanStore = defineStore('userPlan', () => {
    const curDayNum = ref(1)
    const curPlan = ref([])

    const updateCurDayNum = (n) => {
        curDayNum.value=n
    }
    const updateCurPlan = (type, content) => {

    }

    return {curDayNum, curPlan, updateCurDayNum}
})

// pinia
// 상태, getter, setter
// 기본적으로 pinia에서 관리하는 건 다 readOnly 값이다.

//curDayNum: 계획 중인 날
//curPlan: 모든 계획

//curPlan[curDayNum] 반환받아서 그거 출력

//아니 이거 약간 어케야하지.................
//출력 영역 용을 따로 다뤄야 하나....................

//따로 두고, 저장 누를 때 curPlan[curDayNum]을 출력 영역 용으로 갈아끼운다. 
//변화가 생겼지!!! 그러면 ref로 인해 알아서 변경이 감지되고
//