package ru.neoflex.Conveyor.services.algorithm.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.mapper.LoanOfferDTOMapper;
import ru.neoflex.Conveyor.services.algorithm.api.Algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/** Логика расчета предложения при наличии страховки и клиентстве в банке*/
@Service
@RequiredArgsConstructor
public class AlgorithmInsureSalaryImpl implements Algorithm {
    /** Маппер*/
    private final LoanOfferDTOMapper loanOfferDTOMapper;
    private final Random random = new Random();
    /** Годовая ставка клиента по умолчанию*/
    @Value("${rate}")
    private double rate;
    private static final long APPLICATION_ID_MAX = 10000L;
    /** Процент уменьшающий годовую ставку*/
    private static final double REDUCTION_PERCENT_OF_RATE = 3;
    /** Процент берущийся за страховку*/
    private static final double PERCENT_OF_INSURANCE = 10;

    @Override
    public LoanOfferDTO calcOffer(LoanApplicationRequestDTO applicationRequestDTO) {

        /** Идентификатор - псевдослучайное число от 0 до 10000 */
        Long applicationId = random.nextLong(0, APPLICATION_ID_MAX);

        /** Сумма из LoanApplicationRequestDTO(amount), запрошенная клиентом*/
        Long requestAmount = applicationRequestDTO.getAmount();

        /** Сумма рассчитанная с учетом страховки, логики расчета*/
        Long totalAmount = (long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) *
                        (rate / 100 - REDUCTION_PERCENT_OF_RATE / 100)));

        /** Количество месяцев из LoanApplicationRequestDTO(term), указанное клиентом*/
        Integer term = applicationRequestDTO.getTerm();

        /** Ежемесячный платеж*/
        BigDecimal monthlyPayment = BigDecimal.valueOf((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) *
                        (rate / 100 - REDUCTION_PERCENT_OF_RATE / 100))) / applicationRequestDTO.getTerm()).setScale(1, RoundingMode.UP);

        /** Годовая ставка клиента*/
        BigDecimal finalRate = BigDecimal.valueOf((rate / 100) - (REDUCTION_PERCENT_OF_RATE / 100)).setScale(2, RoundingMode.UP);

        /** Наличие страховки у клиента*/
        Boolean isInsuranceEnabled = true;

        /** Является зарплатными клиентом банка*/
        Boolean isSalaryClient = true;

        return loanOfferDTOMapper.toLoanOfferDTO(applicationId, requestAmount, totalAmount, term, monthlyPayment, finalRate, isInsuranceEnabled, isSalaryClient);
    }
}
