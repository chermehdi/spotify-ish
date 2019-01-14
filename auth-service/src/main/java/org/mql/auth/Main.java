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

package org.mql.auth;

import io.helidon.microprofile.server.Server;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.LogManager;
import javax.enterprise.inject.se.SeContainer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.mql.commons.HeartBeats;
import org.quartz.SchedulerException;

/**
 * Main method simulating trigger of main method of the server.
 */
public final class Main {

  public static void main(final String[] args)
      throws IOException, SQLException, SchedulerException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auth-unit");
    Server server = startServer();
    HeartBeats.startHeartBeats("auth-service", "127.0.0.1", server.getPort());
  }

  protected static Server startServer() throws IOException {
    LogManager.getLogManager().readConfiguration(
        Main.class.getResourceAsStream("/logging.properties"));
    Server server = Server.create();
    server.start();
    return server;
  }
}
