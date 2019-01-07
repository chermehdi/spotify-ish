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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.LogManager;

import io.helidon.microprofile.server.Server;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.jdbcx.JdbcDataSource;

/**
 * Main method simulating trigger of main method of the server.
 */
public final class Main {

  public static void main(final String[] args) throws IOException, SQLException {
    org.h2.tools.Server dbServer = org.h2.tools.Server.createTcpServer(args);
    dbServer.start();
    JdbcDataSource jdbcDataSource = new JdbcDataSource();
    jdbcDataSource.setURL("jdbc:h2:mem:db");
    jdbcDataSource.setPassword("sa");
    jdbcDataSource.setUser("sa");
    Connection connection = jdbcDataSource.getConnection();
    System.out.println("connection object " + connection);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("auth-unit");
    EntityManager em = emf.createEntityManager();
    System.out.println("entity manager " + em);
    System.out.println(dbServer.getStatus() + " " + dbServer.getURL());
    startServer();
  }

  protected static Server startServer() throws IOException {

    LogManager.getLogManager().readConfiguration(
        Main.class.getResourceAsStream("/logging.properties"));
    Server server = Server.create();
    server.start();
    return server;
  }
}
