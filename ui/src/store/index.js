import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

Vue.use(Vuex)

const state = {
  auth: {
    token: '',
    user: {},
    isAuthenticated: false
  }
}

const getters = {}

const actions = {
  loginAction(payload) {
    console.log(this)
  }
}

const mutations = {
  SET_USER_TOKEN(state, tokenObject) {
    state.auth.token = tokenObject.token
    localStorage.setItem('_token', state.auth.token)
  }
}

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
})
