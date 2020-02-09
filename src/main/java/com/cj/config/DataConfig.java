package com.cj.config;

import io.quarkus.arc.config.ConfigProperties;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySources({
        @PropertySource("application.properties")
})
public class DataConfig {

//    public Environment env;

    @ConfigProperty(name = "environment", defaultValue = "dev")
    public String environment;

    public DataConfig() {
    }

//    @Autowired
//    public DataConfig(Environment env) {
//        this.env = env;
//    }

    @Bean(name = "querybuilder4jdb_properties")
    public Properties queryBuilder4JDbProperties() {
        String driverClassName = ConfigProvider.getConfig().getValue(environment + ".driver-class-name", String.class);
        String url = ConfigProvider.getConfig().getValue(environment + ".url", String.class);
        String username = ConfigProvider.getConfig().getValue(environment + ".username", String.class);
        String password = ConfigProvider.getConfig().getValue(environment + ".password", String.class);
        String databaseType = ConfigProvider.getConfig().getValue(environment + ".databaseType", String.class);

        Properties properties = new Properties();
        properties.setProperty("driver-class-name", driverClassName);
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        properties.setProperty("databaseType", databaseType);
        return properties;
    }

    @Bean(name = "querybuilder4j.db")
    @Primary
    public DataSource dataSource_querybuilder4j() {
        BasicDataSource ds = new BasicDataSource();

        String driverClassName = ConfigProvider.getConfig().getValue(environment + ".driver-class-name", String.class);
        ds.setDriverClassName(driverClassName);

        String url = ConfigProvider.getConfig().getValue(environment + ".url", String.class);
        ds.setUrl(url);

        String username = ConfigProvider.getConfig().getValue(environment + ".username", String.class);
        ds.setUsername(username);

        String password = ConfigProvider.getConfig().getValue(environment + ".password", String.class);
        ds.setPassword(password);

        return ds;
    }

    @Bean(name = "logging.db")
    public DataSource dataSource_logging() {
        BasicDataSource ds = new BasicDataSource();

        String driverClassName = ConfigProvider.getConfig().getValue(environment + ".logging.datasource.driver-class-name", String.class);
        ds.setDriverClassName(driverClassName);

        String url = ConfigProvider.getConfig().getValue(environment + ".logging.database.url", String.class);
        ds.setUrl(url);

        String username = ConfigProvider.getConfig().getValue(environment + ".logging.database.username", String.class);
        ds.setUsername(username);

        String password = ConfigProvider.getConfig().getValue(environment + ".logging.database.password", String.class);
        ds.setPassword(password);

        return ds;
    }

    @Bean(name = "query_templates.db")
    public DataSource dataSource_queryTemplates() {
        BasicDataSource ds = new BasicDataSource();

        String driverClassName = ConfigProvider.getConfig().getValue(environment + ".query_templates.datasource.driver-class-name", String.class);
        ds.setDriverClassName(driverClassName);

        String url = ConfigProvider.getConfig().getValue(environment + ".query_templates.database.url", String.class);
        ds.setUrl(url);

        String username = ConfigProvider.getConfig().getValue(environment + ".query_templates.database.username", String.class);
        ds.setUsername(username);

        String password = ConfigProvider.getConfig().getValue(environment + ".query_templates.database.password", String.class);
        ds.setPassword(password);

        return ds;
    }
}
