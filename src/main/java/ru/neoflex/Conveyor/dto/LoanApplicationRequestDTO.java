package ru.neoflex.Conveyor.dto;

import java.time.LocalDate;

/* Запрос кредитного предложения */
public class LoanApplicationRequestDTO {
    private Long amount; // сумма, запрошенная клиентом(действительное число, большее или равное 10000)
    private Integer term; // количество месяцев(целове число, большее или равное 6)
    private String firstName; // Имя(от 2 до 30 символов)
    private String lastName; // Фамилия(от 2 до 30 символов)
    private String middleName; // Отчество(при наличии, от 2 до 30 символов)
    private String email; // адрес электронной почты(строка, подходящая под паттерн [\w\.]{2,50}@[\w\.]{2,20})
    private LocalDate birthdate; // дата рождения(число в формате гггг-мм-дд, не позднее 18 лет с текущего дня)
    private String passportSeries; // серия паспорта(4 цифры)
    private String passportNumber; // номер паспорта(6 цифр)
}
