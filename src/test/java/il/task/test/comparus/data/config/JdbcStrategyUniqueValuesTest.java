package il.task.test.comparus.data.config;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class JdbcStrategyUniqueValuesTest {

    @Test
    public void testUniquePropertyNameAndDriverClass() {
        List<String> failMessages = new ArrayList<>();
        JdbcStrategy[] values = JdbcStrategy.values();
        for (int i = 0; i < values.length; i++) {
            for (int j = i; j < values.length; j++) {
                JdbcStrategy strategy1 = values[i];
                JdbcStrategy strategy2 = values[j];
                if (strategy1 != strategy2) {
                    if (Objects.equals(strategy1.getPropertyName(), strategy2.getPropertyName())) {
                        failMessages.add("No unique propertyName value in [" + strategy1 + "] & [" + strategy2 + "]");
                    }

                    if (Objects.equals(strategy1.getDriverClass(), strategy2.getDriverClass())) {
                        failMessages.add("No unique driverClass value in [" + strategy1 + "] & [" + strategy2 + "]");
                    }
                }
            }
        }
        if (failMessages.size() > 0) {
            fail("\n" + String.join("\n", failMessages));
        }
    }
}