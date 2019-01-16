<template>
  <div class="player__container">
    <div class="sidebar">
      <div class="avatar_container">
        <div class="img__container">
          <img :src="user.imgUrl" alt=""/>
        </div>
        <div class="info">
          {{ user.firstName }} {{ user.lastName }}
        </div>
      </div>
    </div>
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
      <audio-player ref="player" :autoplay="true" v-if="music.src !== ''"
                    :music=music></audio-player>
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
      ...mapActions(['getArtists', 'getUserInfo', 'getToken']),
      play(song, artist) {
        this.updateCurrentPlaying(song, artist)
        if (this.$refs.player) {
          this.$refs.player.thenPlay()
        }
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
      ...mapGetters(['artists', 'user'])
    },

    mounted() {
      this.getToken()
      this.getUserInfo()
      this.getArtists()
    }
  }
</script>

<style scoped>

  .player__container {
    height: calc(100vh);
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
    background-color: var(--primary-darker);
    color: var(--light);
    bottom: 0;
    min-width: 100%;
    z-index: 10000;
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

  .img__container {
    width: 100%;
    margin-top: 1rem;
    text-align: center;
  }

  .img__container img {
    max-width: 60%;
    border-radius: 50%;
    border-color: var(--light);
    border: 2px solid;
  }

  .info {
    text-align: center;
    font-size: 1.2rem;
    margin-top: 1rem;
    color: var(--light);
  }

  .player .aplayer {
    margin: 0;
  }
</style>
