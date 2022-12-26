package ru.neoflex.Conveyor.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.neoflex.Conveyor.exception.IncorrectURLException;
import ru.neoflex.Conveyor.exception.ErrorResponse;

import java.time.LocalDateTime;

/** Класс, обрабатывающий исключения*/
@Slf4j
@ControllerAdvice
public class ConveyorControllerAdvice extends ResponseEntityExceptionHandler {

    /** Метод обработки искоючения при неверно указанном URL стороннего сервиса*/
    @ExceptionHandler(IncorrectURLException.class)
    public ResponseEntity<ErrorResponse> handleException(IncorrectURLException e) {
        log.error("Incorrect URL in property file");
        ErrorResponse errorResponse = new ErrorResponse(
                "Incorrect URL in property file",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /** Метод обработки всех исключений*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
