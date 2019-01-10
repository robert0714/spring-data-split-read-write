package develop.app.replicationdatasource.config;
 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import develop.app.replicationdatasource.jpa.User;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = User.class)
public class ReplicationDataSourceApplicationConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
		/****
		 * 去META-INF找persistence.xml進行設定，如果沒有此檔案，則要要設定emfb.setPackagesToScan("...");
		 * emfb.setJpaPropertyMap(..);
		 * emfb.setPersistenceProvider(org.hibernate.jpa.HibernatePersistenceProvider);
		 ****/
        emfb.setPersistenceUnitName("replicationTest"); 
       
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
