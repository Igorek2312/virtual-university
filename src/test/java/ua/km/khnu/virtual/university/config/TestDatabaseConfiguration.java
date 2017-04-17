package ua.km.khnu.virtual.university.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Database config for integration tests
 *
 * @author Igor Rybak
 */
@Configuration
public class TestDatabaseConfiguration {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    /**
     * Prevents from running migrations on startup in test environment.
     */
    @Bean
    public Flyway flyway() {
        return new Flyway();
    }
}
