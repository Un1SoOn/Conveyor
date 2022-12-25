package ru.neoflex.Conveyor.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/** Объект, отправляемый клиенту при неверно указанном URL*/
@Data
@AllArgsConstructor
public class URLErrorResponse {

    /** Собщение ошибки*/
    private String message;
    /** Дата и время возникновения ошибки*/
    private LocalDateTime timestamp;
}
