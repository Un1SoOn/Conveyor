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

/** Логика расчета предложения при отсутствии страховки и отсутствии клиентства в банке*/
@Service
@RequiredArgsConstructor
public class AlgorithmNotInsureSalaryImpl implements Algorithm {
    /** Маппер*/
    private final LoanOfferDTOMapper loanOfferDTOMapper;
    private final Random random = new Random();
    private static final long APPLICATION_ID_MAX = 10000L;
    /** Годовая ставка клиента по умолчанию*/
    @Value("${rate}")
    private double rate;
    /** Процент увеличивающий годовую ставку*/
    private static final double ADDITIONAL_PERCENT_TO_RATE = 13;

    @Override
    public LoanOfferDTO calcOffer(LoanApplicationRequestDTO applicationRequestDTO) {

        /** Идентификатор - псевдослучайное число от 0 до 10000 */
        Long applicationId = random.nextLong(0, APPLICATION_ID_MAX);

        /** Сумма из LoanApplicationRequestDTO(amount), запрошенная клиентом*/
        Long requestAmount = applicationRequestDTO.getAmount();

        /** Сумма рассчитанная с учетом страховки, логики расчета*/
        Long totalAmount = (long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() *
                ((rate / 100) + (ADDITIONAL_PERCENT_TO_RATE / 100))));

        /** Количество месяцев из LoanApplicationRequestDTO(term), указанное клиентом*/
        Integer term = applicationRequestDTO.getTerm();

        /** Ежемесячный платеж*/
        BigDecimal monthlyPayment = BigDecimal.valueOf((applicationRequestDTO.getAmount()+
                (applicationRequestDTO.getAmount() * ((rate / 100) + (ADDITIONAL_PERCENT_TO_RATE / 100)))) /
                applicationRequestDTO.getTerm()).setScale(1, RoundingMode.HALF_UP);

        /** Годовая ставка клиента*/
        BigDecimal finalRate = new BigDecimal((rate / 100) + ADDITIONAL_PERCENT_TO_RATE / 100).setScale(2, RoundingMode.HALF_UP);

        /** Наличие страховки у клиента*/
        Boolean isInsuranceEnabled = false;

        /** Является зарплатными клиентом банка*/
        Boolean isSalaryClient = true;

        return loanOfferDTOMapper.toLoanOfferDTO(applicationId, requestAmount, totalAmount, term, monthlyPayment, finalRate, isInsuranceEnabled, isSalaryClient);
    }
}
