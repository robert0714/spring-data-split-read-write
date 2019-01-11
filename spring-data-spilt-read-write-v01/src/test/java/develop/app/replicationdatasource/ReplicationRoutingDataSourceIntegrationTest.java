package develop.app.replicationdatasource;
 
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import develop.app.replicationdatasource.config.WithRoutingDataSourceConfig;

/**
 * Replication with {@link kr.pe.kwonnam.replicationdatasource.routingdatasource.ReplicationRoutingDataSource}.
 */
@ContextConfiguration(classes = {WithRoutingDataSourceConfig.class})
@DirtiesContext
public class ReplicationRoutingDataSourceIntegrationTest extends AbstractReplicationDataSourceIntegrationTest {
}
