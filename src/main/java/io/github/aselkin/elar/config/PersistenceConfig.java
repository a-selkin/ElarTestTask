package io.github.aselkin.elar.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

@Configuration
@EnableAutoConfiguration
public class PersistenceConfig {
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS =
        "hibernate.current_session_context_class";

    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String userName;
    @Value("${db.password}")
    private String password;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show_sql}")
    private boolean hibernateShowSql;
    @Value("${hibernate.current_session_context_class}")
    private String hibernateCurrentSessionContextClass;
    @Value("${hibernate.packagesToScan}")
    private String packagesToScan;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(userName);
        ds.setPassword(password);
        return ds;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBuilder sb = new LocalSessionFactoryBuilder(dataSource);
        sb.addProperties(getHibernateProperties());
        sb.scanPackages(packagesToScan);
        return sb.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        final HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory);
        return tm;
    }

    private Properties getHibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put(HIBERNATE_SHOW_SQL, hibernateShowSql);
        hibernateProperties.put(HIBERNATE_DIALECT, hibernateDialect);
        hibernateProperties.put(HIBERNATE_HBM2DDL_AUTO, hbm2ddlAuto);
        hibernateProperties.put(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS,
            hibernateCurrentSessionContextClass);
        return hibernateProperties;
    }
}
