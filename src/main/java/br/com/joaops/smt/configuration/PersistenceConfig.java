package br.com.joaops.smt.configuration;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaVendorAdapter;

/**
 *
 * @author João
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"br.com.joaops.smt.repository"})
public class PersistenceConfig {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(this.getPackagesToScan());
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaProperties(this.getAdditionalProperties());
        return em;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    private String[] getPackagesToScan() { //Informa os Pacotes Que Devem Ser Scaneados
        List<String> packages = new ArrayList<>();
        packages.add("br.com.joaops.smt.model");
        return packages.toArray(new String[packages.size()]);
    }
    
    private Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.tenant_identifier_resolver", MyTenantIdentifierResolver.class.getName());
        properties.setProperty("hibernate.multi_tenant_connection_provider", MyMultiTenantConnectionProvider.class.getName());
        properties.setProperty("hibernate.multiTenancy", "DATABASE");
        return properties;
    }
    
}