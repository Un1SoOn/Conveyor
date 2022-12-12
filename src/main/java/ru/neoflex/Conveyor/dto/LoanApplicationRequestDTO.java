package ru.neoflex.Conveyor.dto;

import java.time.LocalDate;

/**
 * Запрос кредитного предложения
 * */
public class LoanApplicationRequestDTO {
    /** Сумма, запрошенная клиентом(действительное число, большее или равное 10000)*/
    private Long amount;
    /** Количество месяцев(целове число, большее или равное 6)*/
    private Integer term;
    /** Имя(от 2 до 30 символов)*/
    private String firstName;
    /** Фамилия(от 2 до 30 символов)*/
    private String lastName;
    /** Отчество(при наличии, от 2 до 30 символов)*/
    private String middleName;
    /** Адрес электронной почты(строка, подходящая под паттерн [\w\.]{2,50}@[\w\.]{2,20})*/
    private String email;
    /** Дата рождения(число в формате гггг-мм-дд, не позднее 18 лет с текущего дня)*/
    private LocalDate birthdate;
    /** Серия паспорта(4 цифры)*/
    private String passportSeries;
    /** Номер паспорта(6 цифр)*/
    private String passportNumber;

    public LoanApplicationRequestDTO() {
    }

    public LoanApplicationRequestDTO(Long amount, Integer term,
                                     String firstName, String lastName, String middleName, String email, LocalDate birthdate,
                                     String passportSeries, String passportNumber) {
        this.amount = amount;
        this.term = term;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthdate = birthdate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "LoanApplicationRequestDTO{" +
                "amount=" + amount +
                ", term=" + term +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
