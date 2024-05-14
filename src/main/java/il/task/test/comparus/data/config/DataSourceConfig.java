package il.task.test.comparus.data.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourceConfig {
    private String name;
    private String strategy;
    private String url;
    private String user;
    private String password;
    private String table;
    private Map<String, String> mapping;
}
