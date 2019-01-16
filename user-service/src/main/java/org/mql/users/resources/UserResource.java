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
import org.mql.users.domain.Profile;
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
      TypedQuery<User> query = em
          .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
      return findUserInfo(email, query);
    });
  }

  private Response findUserInfo(String email, TypedQuery<User> query) {
    query.setParameter("email", email);
    try {
      return Response.ok(query.getSingleResult()).build();
    } catch (Exception e) {
      e.printStackTrace();
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  @GET
  @Path("/me/{email}")
  public Response userInfo(@PathParam("email") String email) {
    return jpa.executeInJpa(em -> {
      TypedQuery<Profile> findUserByEmail = em
          .createQuery("SELECT u.profile FROM User u WHERE u.email = :email", Profile.class);
      findUserByEmail.setParameter("email", email);
      try {
        return Response.ok(findUserByEmail.getSingleResult()).build();
      } catch (Exception e) {
        e.printStackTrace();
        return Response.status(Status.BAD_REQUEST).build();
      }
    });
  }
}
