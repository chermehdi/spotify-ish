#!/usr/bin/env bash

mvn clean package

function print_message() {
  echo "============================================================="
  echo "$1  started"
  echo "============================================================="
}

java -jar service-registry/target/registry.jar

print_message "Service registry"

java -jar api-gateway/target/gateway.jar

print_message "Api gateway"

java -jar artists-service/target/artists.jar

print_message "Artists Service"

node streaming-service/server.js

print_message "Streaming service"

java -jar user-service/target/user-service.jar

print_message "User service"
