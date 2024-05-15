package il.task.test.comparus.controller;

import il.task.test.comparus.data.dao.entity.User;
import il.task.test.comparus.service.DataCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    public DataCollector dataCollector;

    @GetMapping
    public ResponseEntity<List<User>> users() {
        log.info("Request: /users");
        List<User> users = dataCollector.collectData();
        return ResponseEntity.ok(users);
    }
}
