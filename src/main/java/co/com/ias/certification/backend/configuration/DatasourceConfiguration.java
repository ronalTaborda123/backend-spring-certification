package co.com.ias.certification.backend.configuration;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import co.com.ias.certification.backend.configuration.domain.DatabaseCredentials;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @Profile({"dev"})
    public DataSource hikariDatasource(DatabaseCredentials credentials) {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.addDataSourceProperty("databaseName", credentials.getDatabase());
        config.addDataSourceProperty("portNumber", credentials.getPort());
        config.addDataSourceProperty("serverName", credentials.getHost());
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());
        return new HikariDataSource(config);
    }
}
