<template>
  <div class="player__container">
    <div class="sidebar"></div>
    <div class="playing"></div>
    <div class="player"></div>
  </div>
</template>
<script>

</script>

<style scoped>

  .player__container {
    height: calc(100vh - 100px);
    display: grid;
    grid-template-columns: 1fr 5fr;
  }

  .sidebar {
    background-color: var(--primary-dark);
    color: var(--light);
  }

  .player {
    position: fixed;
    box-shadow: -1px 0 2px rgba(0, 0, 0, .3);
    min-height: 100px;
    background-color: var(--primary-darker);
    color: var(--light);
    bottom: 0;
    min-width: 100%;
  }

</style>
