package org.mql.users.resources;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.mql.users.domain.User;
import org.mql.users.helpers.JpaBeanHelper;

/**
 * @author chermehdi
 */
@RequestScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  JpaBeanHelper jpa;

  @GET
  public Response get() {
    List<User> allUsers = jpa.executeInJpa(em -> {
      TypedQuery<User> allUsersQuery = em.createQuery("SELECT u FROM User u", User.class);
      return allUsersQuery.getResultList();
    });
    return Response.ok(allUsers).build();
  }

  @GET
  @Path("/create")
  public Response create() {
    User user = new User("mehdi.cheracher@gmail.com", "123123");
    jpa.executeInJpaTransactional(em -> em.persist(user));
    return Response.ok().build();
  }
}
