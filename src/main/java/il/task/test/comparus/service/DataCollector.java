package il.task.test.comparus.service;

import il.task.test.comparus.data.dao.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataCollector {
    @Autowired
    private List<CustomJdbcTemplate> templates;

    public List<User> collectData() {
        log.info("Collect data");
        List<User> result = new ArrayList<>();
        for (CustomJdbcTemplate template : templates) {
            List<Map<String, Object>> data = template.queryForList("SELECT * FROM " + template.getTable());
            List<User> users = convertByMapping(data, template.getMapping());
            result.addAll(users);
        }
        return result;
    }

    private List<User> convertByMapping(List<Map<String, Object>> data, Map<String, String> mapping) {
        log.info("Convert data by mapping");
        List<User> users = new ArrayList<>();

        for (Map<String, Object> dat : data) {
            User user = new User();
            for (Map.Entry<String, String> map : mapping.entrySet()) {
                String userFieldName = map.getKey();
                String mappedFieldName = map.getValue();
                Object value = dat.get(mappedFieldName);
                setValueByFieldName(user, userFieldName, value);
            }
            users.add(user);
        }

        return users;
    }

    public void setValueByFieldName(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
