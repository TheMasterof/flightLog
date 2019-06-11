#!/bin/sh

cd flightLog
docker-compose up -d

java -jar ./flightLogFXML/out/artifacts/flightLogFXML_jar/flightLogFXML.jar
sleep(5)
