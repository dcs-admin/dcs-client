# dcs-client
dcs-client is a springboot app with client/agent to installed on client machine to track JDBC/Load/Extract operations directly from client with http or tcp protocols

## Manual start of app

Prequiste:
01. Start dcs-kafka-server 
02. Start both Zookeper and kafka sever


```
java -jar -Dserver.port=9999  dcsclient-0.0.1-SNAPSHOT.jar
 
```

## To Make sure data consuming by kafka
```
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic topic --from-beginning

```
 
### Reference
https://dzone.com/articles/netflix-eureka-discovery-microservice
