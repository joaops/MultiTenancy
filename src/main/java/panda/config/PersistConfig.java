package panda.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import panda.config.multitenant.MyMultiTenantConnectionProvider;
import panda.config.multitenant.MyTenantIdentifierResolver;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Feng on 01-Mar-15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"panda.repo"})
public class PersistConfig {
    @Autowired
    private ApplicationContext ctx;

//    @PostConstruct
//    public void examine() throws SQLException {
//        Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
//    }

//    @Bean()
//    public BasicDataSource dataSource() {
//        BasicDataSource driverManagerDataSource = new BasicDataSource();
//        driverManagerDataSource.setDriverClassName("org.h2.Driver");
//        driverManagerDataSource.setUrl("jdbc:h2:~/h2db/master;AUTO_SERVER=true;DB_CLOSE_DELAY=-1");
//        return driverManagerDataSource;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("panda.repo");
//        em.setDataSource(dataSource());

        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaPropertyMap(this.jpaProperties());
        return em;
    }

    @Bean
    public Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");

        props.put("hibernate.tenant_identifier_resolver", MyTenantIdentifierResolver.class.getName());
        props.put("hibernate.multi_tenant_connection_provider", MyMultiTenantConnectionProvider.class.getName());
        props.put("hibernate.multiTenancy", "DATABASE");
        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
