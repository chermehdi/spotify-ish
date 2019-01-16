<template>
  <div class="login__container">
    <div class="first_panel"/>
    <div class="second_panel">
      <div class="content__container">
        <div class="top">
          <header-text text="Spotifyish Music" :dark="true"/>
        </div>
        <div class="middle">
          <h3> Login form</h3>
          <form @submit="login">
            <input type="text" placeholder="your email" v-model="email"/>
            <input type="password" placeholder="your password" v-model="password"/>
            <input type="submit" value="Login"/>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import HeaderText from '@/components/HeaderText'
  import {mapActions} from 'vuex'

  export default {
    components: {HeaderText},
    data() {
      return {
        email: '',
        password: '',
        loading: false
      }
    },
    methods: {
      ...mapActions(['loginAction']),
      login(e) {
        e.preventDefault()
        this.loading = true
        console.log('login action')
        this.loginAction({
          email: this.email,
          password: this.password
        })
      }
    }
  }
</script>
<style scoped>
  .login__container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    height: 100vh;
  }

  .first_panel {
    background: url(../assets/login_splash.jpg) center center no-repeat;
    background-size: cover;
  }

  .top {
    width: 100%;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .content__container {
    padding: 3rem 1rem;
  }

  form {
    display: flex;
    flex-direction: column;
    max-width: 80%;
  }

  form input {
    margin-top: 1rem;
  }
</style>
