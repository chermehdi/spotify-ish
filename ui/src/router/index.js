import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import Player from '@/views/Player'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/player',
      name: 'Player',
      component: Player
    }
  ]
})
