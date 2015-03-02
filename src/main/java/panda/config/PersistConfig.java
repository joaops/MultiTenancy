package panda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Feng on 01-Mar-15.
 */
@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories
public class PersistConfig {
    @Autowired
    private ApplicationContext ctx;

    @PostConstruct
    public void examine() throws SQLException {
        Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
        ResultSet rs = dataSource().getConnection().createStatement().executeQuery("select count(*) from users");
        rs.next();
        System.out.println(rs.getInt(1) + " users in database.");
    }

    @Bean(name = "masterDataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:~/h2db/master;AUTO_SERVER=true;DB_CLOSE_DELAY=-1");
        driverManagerDataSource.setUsername("");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }
}
