package il.task.test.comparus.data.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourceConfig {
    private String name;
    @JsonDeserialize(using = JdbcStrategyDeserializer.class)
    private JdbcStrategy strategy;
    private String url;
    private String user;
    private String password;
    private String table;
    private Map<String, String> mapping;
}
