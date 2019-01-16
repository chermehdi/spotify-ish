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

package org.mql.registry;

import io.helidon.microprofile.server.Server;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import org.mql.registry.jobs.ApplicationStatusChecker;

/**
 * Main method simulating trigger of main method of the server.
 */
public final class Main {

  public static void main(final String[] args) throws IOException {
    Server server = startServer();
    SeContainer container = server.getContainer();
    ApplicationStore store = container.select(ApplicationStore.class).get();
    final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    scheduledExecutorService
        .scheduleAtFixedRate(new ApplicationStatusChecker(store), 0, 1, TimeUnit.MINUTES);
  }

  protected static Server startServer() throws IOException {
    LogManager.getLogManager().readConfiguration(
        Main.class.getResourceAsStream("/logging.properties"));
    Server server = Server.create();
    server.start();
    return server;
  }
}
