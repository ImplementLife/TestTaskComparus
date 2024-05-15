package il.task.test.comparus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import il.task.test.comparus.data.config.DataSourceConfig;
import il.task.test.comparus.data.config.DataSourceConfigList;
import il.task.test.comparus.service.CustomJdbcTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class DataSourcesConfig {
    @Value("classpath:application.yaml")
    private Resource resource;

    private DataSourceConfigList loadDataSources() {
        log.info("Load DataSources from application.yaml config");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try (InputStream inputStream = resource.getInputStream()) {
            return mapper.readValue(inputStream, DataSourceConfigList.class);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private List<DataSourceConfig> dataSources() {
        log.info("Create list dataSources");
        DataSourceConfigList dataSourceConfig = loadDataSources();
        return dataSourceConfig.getDataSources();
    }

    @Bean
    public List<CustomJdbcTemplate> jdbcTemplates() {
        log.info("Create list jdbcTemplates");
        List<CustomJdbcTemplate> templates = new ArrayList<>();
        for (DataSourceConfig config : dataSources()) {
            CustomJdbcTemplate jdbcTemplate = new CustomJdbcTemplate(config);
            templates.add(jdbcTemplate);
        }
        return templates;
    }

}
