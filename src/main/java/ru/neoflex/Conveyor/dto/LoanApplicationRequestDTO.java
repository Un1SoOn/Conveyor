package ru.neoflex.Conveyor.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Запрос кредитного предложения
 * */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequestDTO {
    /** Сумма, запрошенная клиентом(действительное число, большее или равное 10000)*/
    @Min(value = 10000, message="Сумма должна превышать 10000")
    private Long amount;

    /** Количество месяцев(целове число, большее или равное 6)*/
    @Min(value = 6, message="Количество месяцев должно быть больше или равно 6")
    private Integer term;

    /** Имя(от 2 до 30 символов)*/
    @NotNull(message="Имя не может быть пустым")
    @Size(min = 2, max = 30, message="Длинна имени должна быть в промежутке между 2 и 30 символами")
    private String firstName;

    /** Фамилия(от 2 до 30 символов)*/
    @NotNull(message="Фамилия не может быть пустой")
    @Size(min = 2, max = 30, message="Длинна фамилии должна быть в промежутке между 2 и 30 символами")
    private String lastName;

    /** Отчество(при наличии, от 2 до 30 символов)*/
    @Size(min = 2, max = 30, message="Длинна отчества должна быть в промежутке между 2 и 30 символами")
    private String middleName;

    /** Адрес электронной почты(строка, подходящая под паттерн [\w\.]{2,50}@[\w\.]{2,20})*/
    @Email(message = "Неверный формат электронной почты")
    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    private String email;

    /** Дата рождения(число в формате гггг-мм-дд, не позднее 18 лет с текущего дня)*/
    private LocalDate birthdate;

    /** Серия паспорта(4 цифры)*/
    @Size(min = 4, max = 4)
    private String passportSeries;

    /** Номер паспорта(6 цифр)*/
    @Size(min = 6, max = 6)
    private String passportNumber;

}
