package com.example.helloworld;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.helloworld.db.PersonDAO;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = PersonDAO.class)
public class SpringDropWizardConfig {

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @SuppressWarnings("unused")
	private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", H2Dialect.class.getName());
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "true");
        properties.setProperty("hibernate.use_identifier_rollback", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        return properties;
    }

    @SuppressWarnings("unused")
	private String packageFor(Class<?> clazz) {
        return clazz.getPackage().getName();
    }
}
