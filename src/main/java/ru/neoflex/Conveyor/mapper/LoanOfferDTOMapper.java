package ru.neoflex.Conveyor.mapper;

import org.springframework.stereotype.Component;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.dto.OnlineOfferResponseDTO;

import java.math.BigDecimal;

/** Маппер для объектов LoanOfferDTO*/
@Component
public class LoanOfferDTOMapper {
    /** Маппирование OnlineOfferResponseDTO в LoanOfferDTO*/
    public LoanOfferDTO toLoanOfferDTO(OnlineOfferResponseDTO onlineOfferResponseDTO) {
        return LoanOfferDTO.builder().
                applicationId(onlineOfferResponseDTO.getAppId()).
                requestAmount(onlineOfferResponseDTO.getReqAm()).
                totalAmount(onlineOfferResponseDTO.getTotal()).
                term(onlineOfferResponseDTO.getTerm()).
                monthlyPayment(onlineOfferResponseDTO.getMonthlyPay()).
                build();
    }

    /** Маппирование LoanApplicationRequestDTO в LoanOfferDTO*/
    public LoanOfferDTO toLoanOfferDTO(Long applicationId, Long requestAmount, Long totalAmount, Integer term, BigDecimal monthlyPayment,
                                       BigDecimal rate, Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        return LoanOfferDTO.builder().
                applicationId(applicationId).
                requestAmount(requestAmount).
                totalAmount(totalAmount).
                term(term).
                monthlyPayment(monthlyPayment).
                rate(rate).
                isInsuranceEnabled(isInsuranceEnabled).
                isSalaryClient(isSalaryClient).
                build();
    }
}
