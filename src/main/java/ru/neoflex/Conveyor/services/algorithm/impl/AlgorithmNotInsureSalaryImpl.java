package ru.neoflex.Conveyor.services.algorithm.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.AlgorithmNotInsureSalary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/** Логика расчета предложения при отсутствии страховки и отсутствии клиентства в банке*/
@Service
public class AlgorithmNotInsureSalaryImpl implements AlgorithmNotInsureSalary {
    private final Random random = new Random();
    private static final long APPLICATION_ID_MAX = 10000L;
    /** Годовая ставка клиента по умолчанию*/
    @Value("${rate}")
    private double rate;
    /** Процент увеличивающий годовую ставку*/
    private static final double ADDITIONAL_PERCENT_TO_RATE = 13;

    @Override
    public LoanOfferDTO calcOffer(LoanApplicationRequestDTO applicationRequestDTO) {
        return LoanOfferDTO.builder().
                applicationId(random.nextLong(0, APPLICATION_ID_MAX)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * ((rate / 100) + (ADDITIONAL_PERCENT_TO_RATE / 100))))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount()+
                        (applicationRequestDTO.getAmount() * ((rate / 100) + (ADDITIONAL_PERCENT_TO_RATE / 100)))) /
                        applicationRequestDTO.getTerm()).setScale(1, RoundingMode.HALF_UP)).
                rate(new BigDecimal((rate / 100) + ADDITIONAL_PERCENT_TO_RATE / 100).setScale(2, RoundingMode.HALF_UP)).
                isInsuranceEnabled(false).
                isSalaryClient(true).
                build();
    }
}
