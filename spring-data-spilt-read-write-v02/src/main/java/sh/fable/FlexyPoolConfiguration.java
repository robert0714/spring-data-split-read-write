package sh.fable;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;

@org.springframework.context.annotation.Configuration
public class FlexyPoolConfiguration {
	public FlexyPoolConfiguration() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(FlexyPoolConfiguration.class);

	@Bean
	public JdbcEventListener myListener() {
		return new JdbcEventListener() {
			@Override
			public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
				showJdbcUrl(connectionInformation);
				logger.debug("got connection");
			}

			@Override
			public void onAfterConnectionClose(ConnectionInformation connectionInformation, SQLException e) {
//				showJdbcUrl(connectionInformation);
				logger.debug("connection closed");
			}
		};

	}

	protected void showJdbcUrl(final ConnectionInformation connectionInformation) {
		DatabaseMetaData metadata;
		try {
			Connection con = connectionInformation.getConnection();
			if (con.isClosed()) {
				return;
			}
			metadata = con.getMetaData();

			String url = metadata.getURL();
			String username = metadata.getUserName();
			logger.debug("jdbc url: {}", url);
			logger.debug("username: {}", username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
}