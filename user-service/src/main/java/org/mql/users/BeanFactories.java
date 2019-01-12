package org.mql.users;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author chermehdi
 */
@ApplicationScoped
public class BeanFactories {

  @Produces
  public EntityManagerFactory getEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("users-unit");
  }

  @Produces
  public EntityManager getEntityManager() {
    return getEntityManagerFactory().createEntityManager();
  }
}
