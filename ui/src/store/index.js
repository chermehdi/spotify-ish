import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

import router from '../router'

axios.defaults.headers.post['Content-Type'] = 'application/json'

Vue.use(Vuex)

const state = {
  auth: {
    token: '',
    user: {},
    isAuthenticated: false,
  },
  user: {
    userInfo: {}
  },
  artists: [],
  loginState: {
    success: true
  }
}

const getters = {
  artists(store) {
    return store.artists
  },
  user(store) {
    return store.user.userInfo
  }
}

const actions = {
  loginAction(store, payload) {
    store.commit('UPDATE_USER_EMAIL', {email: payload.email})
    axios.post('/api/auth-service/auth/login', payload)
    .then(res => res.data)
    .then(tokenResponse => store.commit('SET_USER_TOKEN', tokenResponse))
    .catch(err => store.commit('LOGIN_ERROR'))
  },
  getArtists(store) {
    axios.get('/api/artist-service/artists')
    .then(res => res.data)
    .then(artists => store.commit('SET_ARTISTS', artists))
  },
  getUserInfo(store) {
    console.log('user info')
    axios.get(`/api/user-service/users/me/${store.state.user.email}`, {
      headers: {
        'Authorization': store.state.auth.token
      }
    })
    .then(res => res.data)
    .then(userInfo => store.commit('UPDATE_USER_INFO', userInfo))
  },
  getToken(store) {
    const tokenResponse = { token: localStorage.getItem('_token')}
    store.commit('SET_USER_TOKEN', tokenResponse)
  }
}

const mutations = {
  SET_USER_TOKEN(state, tokenObject) {
    state.auth.token = tokenObject.token
    localStorage.setItem('_token', state.auth.token)

    axios.defaults.headers.common['Authorization'] = tokenObject.token
    router.push('/player')
  },
  SET_ARTISTS(state, artists) {
    state.artists = artists
  },
  UPDATE_USER_INFO(state, userInfo) {
    state.user.userInfo = userInfo
  },
  UPDATE_USER_EMAIL(state, userEmail) {
    state.user.email = userEmail.email
  },
  LOGIN_ERROR(state) {
    state.loginState.success = false
  }
}

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
})
