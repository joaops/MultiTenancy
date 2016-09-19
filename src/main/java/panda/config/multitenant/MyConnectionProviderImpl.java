package panda.config.multitenant;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Feng on 02-Mar-15.
 */
public class MyConnectionProviderImpl implements ConnectionProvider{
    
    private DriverManagerDataSource dataSource;
    
    public MyConnectionProviderImpl(String database){
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost/"+database);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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