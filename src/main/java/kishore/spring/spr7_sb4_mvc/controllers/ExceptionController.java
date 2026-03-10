package kishore.spring.spr7_sb4_mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        log.info("Exception caught in controller");
        return ResponseEntity.notFound().build();
    }

}
