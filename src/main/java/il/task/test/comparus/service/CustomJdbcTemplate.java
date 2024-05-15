package il.task.test.comparus.service;

import il.task.test.comparus.data.config.DataSourceConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Getter
public class CustomJdbcTemplate extends JdbcTemplate {
    private final Map<String, String> mapping;
    private final String table;

    public CustomJdbcTemplate(DataSourceConfig config) {
        setDataSource(createDataSource(config));
        this.mapping = config.getMapping();
        this.table = config.getTable();
    }

    private DataSource createDataSource(DataSourceConfig config) {
        log.info("Create DataSource: {}", config.getName());
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        dataSource.setDriverClassName(config.getStrategy().getDriverClass());
        return dataSource;
    }
}
