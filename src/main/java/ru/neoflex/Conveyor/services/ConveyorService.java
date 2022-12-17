package ru.neoflex.Conveyor.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ConveyorService {

    @Value("${default.value}")
    private Double DEFAULT_RATE;

    public List<LoanOfferDTO> prepareResponse(LoanApplicationRequestDTO applicationRequestDTO,
                                               BindingResult bindingResult) {
        Random random = new Random();

        List<LoanOfferDTO> loanOfferDTOS = new ArrayList<>();
        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * (DEFAULT_RATE + 0.15)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount()
                                * (DEFAULT_RATE + 0.15)) / applicationRequestDTO.getTerm()).setScale(1, RoundingMode.DOWN)).
                rate(new BigDecimal(DEFAULT_RATE + 0.15).setScale(2, RoundingMode.DOWN)).
                isInsuranceEnabled(false).
                isSalaryClient(false).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * (DEFAULT_RATE + 0.13)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount()+
                                (applicationRequestDTO.getAmount() * (DEFAULT_RATE + 0.13))) /
                                applicationRequestDTO.getTerm()).setScale(1, RoundingMode.DOWN)).
                rate(new BigDecimal(DEFAULT_RATE + 0.13).setScale(2, RoundingMode.DOWN)).
                isInsuranceEnabled(false).
                isSalaryClient(true).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10) +
                        ((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10)) * (DEFAULT_RATE)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10) +
                        ((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10)) * (DEFAULT_RATE))) /
                                applicationRequestDTO.getTerm()).setScale(1, RoundingMode.DOWN)).
                rate(new BigDecimal(DEFAULT_RATE).setScale(2, RoundingMode.DOWN)).
                isInsuranceEnabled(true).
                isSalaryClient(false).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(applicationRequestDTO.getAmount()).
                totalAmount((long) (applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10) +
                        ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * 0.10) * (DEFAULT_RATE - 0.03)))).
                term(applicationRequestDTO.getTerm()).
                monthlyPayment(BigDecimal.valueOf((applicationRequestDTO.getAmount() + (applicationRequestDTO.getAmount() * 0.10) +
                        ((applicationRequestDTO.getAmount() + applicationRequestDTO.getAmount() * 0.10) *
                                (DEFAULT_RATE - 0.03))) / applicationRequestDTO.getTerm()).setScale(1, RoundingMode.DOWN)).
                rate(new BigDecimal(DEFAULT_RATE - 0.03).setScale(2, RoundingMode.DOWN)).
                isInsuranceEnabled(true).
                isSalaryClient(true).
                build());

        return loanOfferDTOS;
    }
}
