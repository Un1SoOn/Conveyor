package ru.neoflex.Conveyor.services.algorithm.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.AlgorithmInsureSalary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/** Логика расчета предложения при наличии страховки и клиентстве в банке*/
@Service
public class AlgorithmInsureSalaryImpl implements AlgorithmInsureSalary {
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
        return LoanOfferDTO.builder().
                applicationId(random.nextLong(0, APPLICATION_ID_MAX)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                        ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) *
                                (rate / 100 - REDUCTION_PERCENT_OF_RATE / 100)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                        ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) *
                                (rate / 100 - REDUCTION_PERCENT_OF_RATE / 100))) / applicationRequestDTO.getTerm()).setScale(1, RoundingMode.UP)).
                rate(BigDecimal.valueOf((rate / 100) - (REDUCTION_PERCENT_OF_RATE / 100)).setScale(2, RoundingMode.UP)).
                isInsuranceEnabled(true).
                isSalaryClient(true).
                build();
    }
}
