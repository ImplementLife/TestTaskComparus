package il.task.test.comparus.service;

import il.task.test.comparus.data.config.DataSourceConfig;
import il.task.test.comparus.data.dao.entity.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataCollector {
    @Autowired
    private List<DataSourceConfig> dataSources;
    private List<JdbcWithMapping> templates;

    @Getter
    private static class JdbcWithMapping extends JdbcTemplate {
        private final Map<String, String> mapping;
        private final String table;

        public JdbcWithMapping(DataSource dataSource, Map<String, String> mapping, String table) {
            super(dataSource);
            this.mapping = mapping;
            this.table = table;
        }
    }

    @PostConstruct
    private void postConstruct() {
        log.info("Post construct");
        templates = new ArrayList<>();
        for (DataSourceConfig config : dataSources) {
            DataSource dataSource = createDataSource(config);
            JdbcWithMapping jdbcTemplate = new JdbcWithMapping(dataSource, config.getMapping(), config.getTable());
            templates.add(jdbcTemplate);
        }
    }

    public List<User> collectData() {
        log.info("Collect data");
        List<User> result = new ArrayList<>();
        for (JdbcWithMapping template : templates) {
            List<Map<String, Object>> data = template.queryForList("SELECT * FROM " + template.getTable());
            List<User> users = convertByMapping(data, template.getMapping());
            result.addAll(users);
        }
        return result;
    }

    private DataSource createDataSource(DataSourceConfig config) {
        log.info("Create DataSource: {}", config.getName());
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    private List<User> convertByMapping(List<Map<String, Object>> data, Map<String, String> mapping) {
        log.info("Convert by mapping");
        List<User> users = new ArrayList<>();

        for (Map<String, Object> dat : data) {
            User user = new User();
            for (Map.Entry<String, String> map : mapping.entrySet()) {
                String key = map.getKey();
                String value = map.getValue();
                Object entityValue = dat.get(value);
                setValueByFieldName(user, key, entityValue);
            }
            users.add(user);
        }

        return users;
    }

    public void setValueByFieldName(Object obj, String fieldName, Object value) {
        log.trace("Set value by field name: {} {}", fieldName, value);
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
