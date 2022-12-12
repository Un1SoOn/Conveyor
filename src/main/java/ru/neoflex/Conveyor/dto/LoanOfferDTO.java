package ru.neoflex.Conveyor.dto;

import java.math.BigDecimal;

/*Элемент списка кредитного предложения*/
public class LoanOfferDTO {
    private Long applicationId; // идентификатор
    private Long requestAmount; // сумма из LoanApplicationRequestDTO(amount) - которую запросил клиент
    private Long totalAmount; // сумма рассчитанная с учетом страховки, логики расчета
    private Integer term; // количество месяцев из LoanApplicationRequestDTO(term) - указано клиентом
    private BigDecimal monthlyPayment; // ежемесячный платеж
    private BigDecimal rate; // годовая ставка клиента
    private Boolean isInsuranceEnabled; // страховка имеется - true, не имеется - false
    private Boolean isSalaryClient; // является зарплатным клиентом банка - true, не является - false
}
