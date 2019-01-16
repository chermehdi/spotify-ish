package org.mql.artists.service;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.mql.artists.domain.Artist;
import org.mql.artists.repository.ArtistRepository;

/**
 * Delegate service to the {@link ArtistRepository}
 *
 * @author chermehdi
 */
@ApplicationScoped
public class ArtistService {

  @Inject
  ArtistRepository repo;

  public List<Artist> getAllArtists() {
    return repo.findAll();
  }

  public Artist save(Artist artist) {
    return repo.save(artist);
  }
}
