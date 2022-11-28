cd ServerWS
mvn clean install

cd target
mv ServerWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar ..
cd ..
mv ServerWS-0.0.1-SNAPSHOT-jar-with-dependencies.jar ..
cd ..

cd ClientWS
mvn clean install

cd target
mv ClientWS-0.0.1-SNAPSHOT.war ..
cd ..
mv ClientWS-0.0.1-SNAPSHOT.war ..
cd ..

cd ClientMobileWS
mvn clean install

cd target
mv ClienteMobileWS-0.0.1-SNAPSHOT.war ..
cd ..
mv ClienteMobileWS-0.0.1-SNAPSHOT.war ..
cd ..