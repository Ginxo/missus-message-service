package io.missus.messageservice.config;

import io.missus.messageservice.data.repository.MessageRepository;
import lombok.extern.java.Log;
import me.prettyprint.hector.api.factory.HFactory;
import org.cassandraunit.DataLoader;
import org.cassandraunit.dataset.yaml.ClassPathYamlDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Log
@Configuration
@EnableCassandraRepositories(basePackageClasses = MessageRepository.class)
public class CassandraTestConfig extends AbstractCassandraConfiguration {

    private static final String TEST_CLUSTER = "TestCluster";
    private static final String TEST_CLUSTER_PORT = "localhost:9171";

    @Value("${service.cassandra.hosts}")
    private String cassandraHosts;
    @Value("${service.cassandra.port}")
    private String cassandraPort;
    @Value("${service.cassandra.keyspace}")
    private String cassandraKeySpace;

    @Override
    public String getKeyspaceName() {
        return cassandraKeySpace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster;
        try {
            EmbeddedCassandraServerHelper.startEmbeddedCassandra();


            final DataLoader dataLoader = new DataLoader(TEST_CLUSTER, TEST_CLUSTER_PORT);
            dataLoader.load(new ClassPathYamlDataSet("simpleDataSet.yml"));
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw new RuntimeException("Can't start Embedded Cassandra.", e);
        }

        cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(cassandraHosts);
        cluster.setPort(Integer.parseInt(cassandraPort));
        HFactory.createKeyspace(cassandraKeySpace, HFactory.getOrCreateCluster(TEST_CLUSTER, TEST_CLUSTER_PORT));

        return cluster;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{
                "io.missus.messageservice.data.repository"};
    }
}

