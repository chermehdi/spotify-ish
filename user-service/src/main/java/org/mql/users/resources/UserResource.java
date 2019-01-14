package org.mql.users.resources;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
  @Path("/{email}")
  public Response findUser(@PathParam("email") String email) {
    return jpa.executeInJpa(em -> {
      TypedQuery<User> findUserByEmail = em
          .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
      findUserByEmail.setParameter("email", email);
      try {
        return Response.ok(findUserByEmail.getSingleResult()).build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(Status.BAD_REQUEST).build();
      }
    });
  }

  @GET
  @Path("/create")
  public Response create() {
    User user = new User("mehdi.cheracher@gmail.com", "123123");
    jpa.executeInJpaTransactional(em -> em.persist(user));
    return Response.ok().build();
  }
}
