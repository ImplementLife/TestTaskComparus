package il.task.test.comparus.controller.except;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        log.error("Handled exception by url: " + requestURL, ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("path", requestURL);
        body.put("message", ex.getMessage());
        return ResponseEntity.internalServerError().body(body);
    }
}
