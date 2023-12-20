package com.example.demoapp.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Handler eccezioni generali
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {

        //log.error("Exception managed: {}", exception.getMessage(), exception);
        log.error("Exception managed: {}", exception.getMessage());
        return ResponseEntity.internalServerError().body(exception.getMessage());

    }

    // handler eccezioni specifico per InvalidCredentialsException
    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException exception) {

        //log.error("Exception managed: {}", exception.getMessage(), exception);
        log.error("Exception managed: {}", exception.getMessage());
        return ResponseEntity.badRequest().body("Wrong username or password");

    }

    // handler eccezioni specifico per UserAlreadyExistsException
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {

        //log.error("Exception managed: {}", exception.getMessage(), exception);
        log.error("Exception managed: {}", exception.getMessage());
        return ResponseEntity.badRequest().body("User already exists");

    }

    // handler eccezioni specifico per UserNotFoundException
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {

        //log.error("Exception managed: {}", exception.getMessage(), exception);
        log.error("Exception managed: {}", exception.getMessage());
        return ResponseEntity.notFound().build();

    }

}
