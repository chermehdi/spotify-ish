<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style>
  * {
    box-sizing: border-box;
  }

  :root {
    --primary: #1B98E0;
    --accent: #247BA0;
    --light: #E8F1F2;
    --primary-darker: #13293D;
    --primary-dark: #006494;
  }

  html, body {
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
  }
</style>
