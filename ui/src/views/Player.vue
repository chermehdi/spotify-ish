<template>
  <div class="player__container">
    <div class="sidebar"></div>
    <div class="playing">
      <h1 class="header">Available Artists</h1>
      <div class="Wrap">
        <div class="Accordion">
          <expander :title="artist.name" v-for="artist in artists" :key="artist.name">
            <ul>
              <li v-for="song in artist.songs" :key="song.name">
                <div class="song" @click="play(song, artist)"> {{ song.name }}</div>
              </li>
            </ul>
          </expander>
        </div>
      </div>
    </div>
    <div class="player">
      <audio-player :autoplay="true" v-if="music.src !== ''" :music=music></audio-player>
    </div>
  </div>
</template>
<script>
  import AudioPlayer from 'vue-aplayer'
  import Expander from '@/components/Expander'
  import {mapActions, mapGetters} from 'vuex'

  export default {
    components: {
      AudioPlayer,
      Expander
    },
    data() {
      return {
        music: {
          title: '',
          artist: '',
          src: '',
          pic: ''
        }
      }
    },
    methods: {
      ...mapActions(['getArtists']),
      play(song, artist) {
        this.updateCurrentPlaying(song, artist)
      },
      updateCurrentPlaying(song, artist) {
        const songUrl = `http://localhost:8080${song.url}`
        let temp = {
          src: songUrl,
          title: song.name,
          pic: artist.picture
        }
        this.music = {...this.music, ...temp}
      }
    },
    computed: {
      ...mapGetters(['artists'])
    },

    mounted() {
      this.getArtists()
    }
  }
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

  ul {
    list-style: none;
  }

  .song {
    background-color: var(--primary-dark);
    color: var(--light);
    padding: 1rem 2rem;
    border-radius: 1rem;
    cursor: pointer;
    margin-top: .5rem;
    transition: all .5s cubic-bezier(.54, .05, .07, .87);
  }

  .song:hover {
    background-color: var(--light);
    color: var(--primary-dark);
    box-shadow: 1px 1px 3px rgba(0, 0, 0, .3);
  }

  .header {
    padding: 2rem 1rem;
    max-width: 90%;
    margin: 0 auto;
  }

  .Wrap {
    max-width: 90%;
    margin: 0 auto;
  }
</style>
