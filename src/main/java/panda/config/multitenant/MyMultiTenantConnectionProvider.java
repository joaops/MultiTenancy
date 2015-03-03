package panda.config.multitenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Feng on 02-Mar-15.
 */
public class MyMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

    private HashMap<String, ConnectionProvider> connProviderMap = new HashMap<>();

    public MyMultiTenantConnectionProvider() {
        for (String providerName : Arrays.asList(new String[]{"master", "tenant1", "tenant2"})) {
            System.out.println("Creating connection pools: " + providerName);
            connProviderMap.put(providerName, new MyConnectionProviderImpl(providerName));
        }
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        System.out.println("Getting any connection");
        try {
            System.out.println(connProviderMap.get("master").getConnection().getMetaData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connProviderMap.get("master");
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String s) {
        System.out.println("Getting connection pools: " + s);
        return connProviderMap.get(s) != null ? connProviderMap.get(s) : new MyConnectionProviderImpl("master");
    }
}
