# Missus Message Service
Missus' message service provides messaging functionalities (storing, retrieving, searching, websocket protocol, ....) to the missus infrastructure.

## How to run it up

### Local environment

Requirements:
* JDK 8
* Maven
* Docker + Docker Compose

#### Cassandra Instance
If this is the first time you run it, you should build it:
```
docker build -t cassandra:dev -f src/main/docker/cassandra/Dockerfile .
```

Once you have it built (check it with ``` docker images -f=reference='cassandra:dev' ```) just run it

```
docker run --rm -i -p 9042:9042 --name cassandra cassandra:dev
```

#### Java stuff
No too much to say here

``` 
mvn clean install -DskipTests=true
mvn spring-boot:run
```

### Production Environment
To be defined...