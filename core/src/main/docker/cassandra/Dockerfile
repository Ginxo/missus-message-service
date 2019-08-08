FROM cassandra:3.11

EXPOSE 9042

COPY src/main/docker/cassandra/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod a+x docker-entrypoint.sh

COPY src/main/docker/cassandra/initial-seed.cql docker-entrypoint-initdb.d/initial-seed.cql