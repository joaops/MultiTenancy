package panda.config.multitenant;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Feng on 02-Mar-15.
 */
public class MyConnectionProviderImpl implements ConnectionProvider{

    private BasicDataSource basicDataSource;

    public MyConnectionProviderImpl(String database){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:~/h2db/" + database + ";AUTO_SERVER=true;DB_CLOSE_DELAY=-1");
        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(10);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();

    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
