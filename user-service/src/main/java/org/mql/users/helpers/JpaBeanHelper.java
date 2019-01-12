package org.mql.users.helpers;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author chermehdi
 */
@ApplicationScoped
public class JpaBeanHelper {

  @Inject
  EntityManager em;

  public void executeInJpa(Consumer<EntityManager> consumer) {
    consumer.accept(em);
  }

  public void executeInJpaTransactional(Consumer<EntityManager> consumer) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    consumer.accept(em);
    transaction.commit();
  }

  public <T> T executeInJpa(Function<EntityManager, T> function) {
    return function.apply(em);
  }

}
