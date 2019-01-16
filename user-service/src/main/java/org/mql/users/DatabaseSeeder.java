package org.mql.users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.mql.users.domain.Profile;
import org.mql.users.domain.User;
import org.mql.users.helpers.JpaBeanHelper;

/**
 * @author chermehdi
 */
@ApplicationScoped
public class DatabaseSeeder {

  @Inject
  JpaBeanHelper jpa;

  public void run() {
    User user = new User("mehdi.cheracher@gmail.com", "123123");
    Profile profile = new Profile("Cheracher", "Mehdi", "https://chermehdi.github.io/img/me.jpg");
    user.setProfile(profile);
    jpa.executeInJpaTransactional(em -> em.persist(user));
  }
}
