#!/bin/sh


docker-compose up -d

java -jar ./flightLogFXML/out/artifacts/flightLogFXML_jar/flightLogFXML.jar

docker-compose down
