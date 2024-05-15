package il.task.test.comparus.data.config;

import lombok.Getter;

@Getter
public enum JdbcStrategy {
    POSTGRES("postgres", "org.postgresql.Driver"),
    MYSQL("mysql", "com.mysql.cj.jdbc.Driver"),
    ;

    private final String propertyName;
    private final String driverClass;

    JdbcStrategy(String propertyName, String driverClass) {
        this.propertyName = propertyName;
        this.driverClass = driverClass;
    }
}
