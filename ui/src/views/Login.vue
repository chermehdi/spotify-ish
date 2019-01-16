<template>
  <div class="login__container">
    <div class="first_panel"/>
    <div class="second_panel">
      <div class="content__container">
        <div class="top">
          <header-text text="Spotifyish Music" :dark="true"/>
        </div>
        <div class="middle">
          <h2> Login form</h2>
          <form @submit="login">
            <div class="group">
              <input type="text" v-model="email" required>
              <span class="highlight"></span>
              <span class="bar"></span>
              <label>Your Email</label>
            </div>
            <div class="group">
              <input type="password" v-model="password" required>
              <span class="highlight"></span>
              <span class="bar"></span>
              <label>Your password</label>
            </div>
            <input type="submit" class="pure-material-button-contained" value="Login"/>
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

  .group {
    position: relative;
    margin-bottom: 15px;
  }

  input {
    font-size: 16px;
    padding: 5px 5px 5px 2px;
    display: block;
    width: 100%;
    border: none;
    border-bottom: 1px solid #757575;
  }

  input:focus {
    outline: none;
  }

  /* LABEL ======================================= */
  label {
    color: var(--primary-dark);
    font-size: 18px;
    font-weight: normal;
    position: absolute;
    pointer-events: none;
    left: 5px;
    top: 10px;
    transition: 0.2s ease all;
    -moz-transition: 0.2s ease all;
    -webkit-transition: 0.2s ease all;
  }

  /* active state */
  input:focus ~ label, input:valid ~ label {
    top: -10px;
    font-size: 14px;
    color: var(--primary-dark);
  }

  /* BOTTOM BARS ================================= */
  .bar {
    position: relative;
    display: block;
    width: 100%;
  }

  .bar:before, .bar:after {
    content: '';
    height: 2px;
    width: 0;
    bottom: 1px;
    position: absolute;
    background: #5264AE;
    transition: 0.2s ease all;
    -moz-transition: 0.2s ease all;
    -webkit-transition: 0.2s ease all;
  }

  .bar:before {
    left: 50%;
  }

  .bar:after {
    right: 50%;
  }

  /* active state */
  input:focus ~ .bar:before, input:focus ~ .bar:after {
    width: 50%;
  }

  /* HIGHLIGHTER ================================== */
  .highlight {
    position: absolute;
    height: 60%;
    width: 100px;
    top: 25%;
    left: 0;
    pointer-events: none;
    opacity: 0.5;
  }

  /* active state */
  input:focus ~ .highlight {
    -webkit-animation: inputHighlighter 0.3s ease;
    -moz-animation: inputHighlighter 0.3s ease;
    animation: inputHighlighter 0.3s ease;
  }

  /* ANIMATIONS ================ */
  @-webkit-keyframes inputHighlighter {
    from {
      background: var(--primary-dark);
    }
    to {
      width: 0;
      background: transparent;
    }
  }

  @-moz-keyframes inputHighlighter {
    from {
      background: var(--primary-dark);
    }
    to {
      width: 0;
      background: transparent;
    }
  }

  @keyframes inputHighlighter {
    from {
      background: var(--primary-dark);
    }
    to {
      width: 0;
      background: transparent;
    }
  }

  .pure-material-button-contained {
    position: relative;
    display: inline-block;
    box-sizing: border-box;
    border: none;
    border-radius: 4px;
    padding: 0 16px;
    min-width: 64px;
    height: 36px;
    vertical-align: middle;
    text-align: center;
    text-overflow: ellipsis;
    text-transform: uppercase;
    color: var(--light);
    background-color: rgb(0, 126, 255);
    box-shadow: 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
    font-size: 14px;
    font-weight: 500;
    line-height: 36px;
    overflow: hidden;
    outline: none;
    cursor: pointer;
    transition: box-shadow 0.2s;
  }

  .pure-material-button-contained::-moz-focus-inner {
    border: none;
  }

  /* Overlay */
  .pure-material-button-contained::before {
    content: "";
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: var(--light);
    opacity: 0;
    transition: opacity 0.2s;
  }

  /* Ripple */
  .pure-material-button-contained::after {
    content: "";
    position: absolute;
    left: 50%;
    top: 50%;
    border-radius: 50%;
    padding: 50%;
    width: 32px; /* Safari */
    height: 32px; /* Safari */
    background-color: var(--light);
    opacity: 0;
    transform: translate(-50%, -50%) scale(1);
    transition: opacity 1s, transform 0.5s;
  }

  /* Hover, Focus */
  .pure-material-button-contained:hover,
  .pure-material-button-contained:focus {
    box-shadow: 0 2px 4px -1px rgba(0, 0, 0, 0.2), 0 4px 5px 0 rgba(0, 0, 0, 0.14), 0 1px 10px 0 rgba(0, 0, 0, 0.12);
  }

  .pure-material-button-contained:hover::before {
    opacity: 0.08;
  }

  .pure-material-button-contained:focus::before {
    opacity: 0.24;
  }

  .pure-material-button-contained:hover:focus::before {
    opacity: 0.3;
  }

  /* Active */
  .pure-material-button-contained:active {
    box-shadow: 0 5px 5px -3px rgba(0, 0, 0, 0.2), 0 8px 10px 1px rgba(0, 0, 0, 0.14), 0 3px 14px 2px rgba(0, 0, 0, 0.12);
  }

  .pure-material-button-contained:active::after {
    opacity: 0.32;
    transform: translate(-50%, -50%) scale(0);
    transition: transform 0s;
  }

  /* Disabled */
  .pure-material-button-contained:disabled {
    color: rgba(0, 0, 0, 0.38);
    background-color: rgba( 0, 0, 0, 0.12);
    box-shadow: none;
    cursor: initial;
  }

  .pure-material-button-contained:disabled::before {
    opacity: 0;
  }

  .pure-material-button-contained:disabled::after {
    opacity: 0;
  }
</style>
