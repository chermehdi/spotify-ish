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
  artists: []
}

const getters = {
  artists(store) {
    return store.artists
  }
}

const actions = {
  loginAction(store, payload) {
    axios.post('/api/auth-service/auth/login', payload)
    .then(res => res.data)
    .then(tokenResponse => store.commit('SET_USER_TOKEN', tokenResponse))
    .catch(err => console.log('the error is ', err))
  },
  getArtists(store) {
    axios.get('/api/artist-service/artists')
    .then(res => res.data)
    .then(artists => store.commit('SET_ARTISTS', artists))
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
  }
}

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations
})
