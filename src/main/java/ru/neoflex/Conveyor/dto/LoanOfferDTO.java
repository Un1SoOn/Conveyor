package ru.neoflex.Conveyor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

/**
 * Элемент списка кредитного предложения
 * */

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoanOfferDTO {
    /** Идентификатор*/
    private Long applicationId;
    /** Сумма из LoanApplicationRequestDTO(amount), запрошенная клиентом*/
    private Long requestAmount;
    /** Сумма рассчитанная с учетом страховки, логики расчета*/
    private Long totalAmount;
    /** Количество месяцев из LoanApplicationRequestDTO(term), указанное клиентом*/
    private Integer term;
    /** Ежемесячный платеж*/
    private BigDecimal monthlyPayment;
    /** Годовая ставка клиента*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal rate;
    /** Если страховка имеется - true, не имеется - false*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isInsuranceEnabled;
    /** Является зарплатным клиентом банка - true, не является - false*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isSalaryClient;
}
