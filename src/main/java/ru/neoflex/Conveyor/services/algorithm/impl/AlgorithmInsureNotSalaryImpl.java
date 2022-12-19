package ru.neoflex.Conveyor.services.algorithm.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.AlgorithmInsureNotSalary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/** Логика расчета предложения при наличии страховки и отсутствия клиентства в банке*/
@Service
public class AlgorithmInsureNotSalaryImpl implements AlgorithmInsureNotSalary {
    private final Random random = new Random();
    private static final long APPLICATION_ID_MAX = 10000L;
    /** Годовая ставка клиента по умолчанию*/
    @Value("${rate}")
    private double rate;
    /** Процент берущийся за страховку*/
    private static final double PERCENT_OF_INSURANCE = 10;

    @Override
    public LoanOfferDTO calcOffer(LoanApplicationRequestDTO applicationRequestDTO) {
        return LoanOfferDTO.builder().
                applicationId(random.nextLong(0,APPLICATION_ID_MAX)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                        ((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100)) * (rate/ 100)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100) +
                        ((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * PERCENT_OF_INSURANCE / 100)) * (rate / 100))) /
                        applicationRequestDTO.getTerm()).setScale(1, RoundingMode.HALF_UP)).
                rate(new BigDecimal(rate / 100).setScale(2, RoundingMode.HALF_UP)).
                isInsuranceEnabled(true).
                isSalaryClient(false).
                build();
    }
}
