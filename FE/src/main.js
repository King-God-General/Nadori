import '@/assets/tailwind.css'

import { createApp } from 'vue'
import { createNaverMap } from 'vue3-naver-maps'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import { createPinia } from 'pinia'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const app = createApp(App)

app.component('VueDatePicker', VueDatePicker)


app.use(createPinia());
app.use(VueSweetalert2);
app.use(router)
app.use(createNaverMap, {
  clientId: 'pbhqhpu7po', // Required
  category: 'ncp', // Optional
  subModules: [] // Optional
})

app.mount('#app')
