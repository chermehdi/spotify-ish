package org.mql.artists;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.mql.artists.domain.Artist;
import org.mql.artists.domain.Song;
import org.mql.artists.service.ArtistService;

/**
 * Seed Database default test data to be used in the demo, this class is to be scanned by the CDI
 * container, and retrieved so that it's run method can be run at application bootstrap
 *
 * @author chermehdi
 */
@ApplicationScoped
public class DataBaseSeeder {

  @Inject
  ArtistService artistService;

  public void run() {
    InputStream is = loadDataStream();
    JsonReader reader = Json.createReader(is);
    JsonArray jsonArray = reader.readArray();
    List<Artist> artists = createArtists(jsonArray);
    persistArtists(artists);
  }

  private void persistArtists(List<Artist> artists) {
    artists.forEach(artistService::save);
    artists.forEach(System.out::println);
  }

  private List<Artist> createArtists(JsonArray jsonArray) {
    return jsonArray.stream().map(jsonValue -> {
      JsonObject jsonObject = jsonValue.asJsonObject();
      Artist artist = new Artist();
      artist.setName(jsonObject.getString("name"));
      artist.setDescription(jsonObject.getString("description"));
      artist.setPicture(jsonObject.getString("picture"));
      artist.setSongs(resolveSongs(jsonObject.getJsonArray("songs")));
      return artist;
    }).collect(Collectors.toList());
  }

  private List<Song> resolveSongs(JsonArray songs) {
    return songs.stream()
        .map(jsonValue -> {
          JsonObject object = jsonValue.asJsonObject();
          return new Song(object.getString("name"), object.getString("url"));
        }).collect(Collectors.toList());
  }

  private InputStream loadDataStream() {
    return DataBaseSeeder.class.getResourceAsStream("/song-data.json");
  }
}
