package org.mql.artists.repository;

/**
 * @author chermehdi
 */

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import org.mql.artists.domain.Artist;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  EntityManager em;

  public List<Artist> findAll() {
    TypedQuery<Artist> query = em
        .createQuery("SELECT a FROM Artist a", Artist.class);
    return query.getResultList();
  }

  public Artist save(Artist artist) {
    return doTransactionaly(entityManager -> {
      em.persist(artist);
      return artist;
    });
  }

  public List<Artist> saveAll(List<Artist> artists) {
    return artists.stream()
        .map(this::save)
        .collect(Collectors.toList());
  }

  public <T> T doTransactionaly(Function<EntityManager, T> consumer) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    T returnValue = consumer.apply(em);
    transaction.commit();
    return returnValue;
  }
}
