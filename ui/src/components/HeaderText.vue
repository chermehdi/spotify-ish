<template>
  <h1 class="header__text" :class="dark ? 'dark': ''">
    {{ text }}
  </h1>
</template>

<script>
  export default {
    props: {
      text: String,
      dark: {
        required: false
      }
    }
  }
</script>

<style scoped>
  .header__text {
    color: var(--light);
    font-size: 2rem;
  }

  .header__text.dark {
    color: var(--primary-darker)
  }
</style>
