#!/bin/sh

set -xe

mvn -q package && \
mv /usr/src/app/target/Cash_Manager-1.0-SNAPSHOT.jar /dist/Cash_Manager-1.0-SNAPSHOT.jar
