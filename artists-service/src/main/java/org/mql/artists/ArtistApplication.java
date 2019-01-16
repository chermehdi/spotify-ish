/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mql.artists;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.mql.artists.resources.ArtistsResource;

/**
 * Bootstrapping class that defines the root of the Jax-rs resources and also the resources implied
 * {@link ArtistApplication#getClasses()}
 */
@ApplicationScoped
@ApplicationPath("/")
public class ArtistApplication extends Application {

  private final Set<Class<?>> resources = new HashSet<>(
      Arrays.asList(
          ArtistsResource.class
      )
  );

  @Override
  public Set<Class<?>> getClasses() {
    return Collections.unmodifiableSet(resources);
  }

  @Produces
  public EntityManager em() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("artists-unit");
    return emf.createEntityManager();
  }
}
