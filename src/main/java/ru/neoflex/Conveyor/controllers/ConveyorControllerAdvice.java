package ru.neoflex.Conveyor.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.neoflex.Conveyor.exception.IncorrectURLException;
import ru.neoflex.Conveyor.exception.URLErrorResponse;

import java.time.LocalDateTime;

/** Класс, обрабатывающий исключения*/
@Slf4j
@ControllerAdvice
public class ConveyorControllerAdvice extends ResponseEntityExceptionHandler {

    /** Метод обработки искоючения при неверно указанном URL стороннего сервиса*/
    @ExceptionHandler
    public ResponseEntity<URLErrorResponse> handleException(IncorrectURLException e) {
        log.error("Incorrect URL in property file");
        URLErrorResponse urlErrorResponse = new URLErrorResponse(
                "Incorrect URL in property file",
                LocalDateTime.now());

        return new ResponseEntity<>(urlErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
