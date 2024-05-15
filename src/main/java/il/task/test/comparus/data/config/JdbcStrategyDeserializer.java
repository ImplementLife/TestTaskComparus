package il.task.test.comparus.data.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class JdbcStrategyDeserializer extends JsonDeserializer<JdbcStrategy> {
    @Override
    public JdbcStrategy deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String strategyName = jsonParser.getText();
        for (JdbcStrategy strategy : JdbcStrategy.values()) {
            if (strategy.getPropertyName().equals(strategyName)) {
                return strategy;
            }
        }
        throw new IllegalArgumentException("Driver for strategy: [" + strategyName + "] doesn't support;");
    }
}
