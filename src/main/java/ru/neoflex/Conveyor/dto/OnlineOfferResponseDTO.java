package ru.neoflex.Conveyor.dto;

import lombok.*;

import java.math.BigDecimal;

/** Предложение, получаемое со стороннего API*/
@Data
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OnlineOfferResponseDTO {
    /** Идентификатор*/
    private Long appId;
    /** Сумма, запрошенная клиентом*/
    private Long reqAm;
    /** Сумма рассчитанная с учетом страховки, логики расчета*/
    private Long total;
    /** Количество месяцев, указанное клиентом*/
    private Integer term;
    /** Ежемесячный платеж*/
    private BigDecimal monthlyPay;
}
