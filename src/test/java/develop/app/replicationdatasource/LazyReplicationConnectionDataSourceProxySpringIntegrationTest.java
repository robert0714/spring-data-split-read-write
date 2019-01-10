package develop.app.replicationdatasource;
 
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import develop.app.replicationdatasource.config.WithLazyReplicationConnectionDataSourceProxyConfig;

/**
 * Replication with {@link LazyReplicationConnectionDataSourceProxy}.
 */
@ContextConfiguration(classes = {WithLazyReplicationConnectionDataSourceProxyConfig.class})
@DirtiesContext
public class LazyReplicationConnectionDataSourceProxySpringIntegrationTest extends AbstractReplicationDataSourceIntegrationTest {
}
