package ru.neoflex.Conveyor.mapper;

import org.springframework.stereotype.Component;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.dto.OnlineOfferResponseDTO;

import java.math.BigDecimal;

/** Маппер для объектов LoanOfferDTO*/
@Component
public class LoanOfferDTOMapper {

    /**
     * Мэппинг в кредитное предложение
     *
     * @param applicationId Идентификатор
     * @param requestAmount сумма, запрошенная клиентом
     * @param totalAmount Сумма рассчитанная с учетом страховки, логики расчета
     * @param term Годовая ставка клиента
     * @param monthlyPayment Ежемесячный платеж
     * */
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

    /** Мэппинг OnlineOfferResponseDTO в LoanOfferDTO*/
    public LoanOfferDTO toLoanOfferDTO(OnlineOfferResponseDTO onlineOfferResponseDTO) {
        return LoanOfferDTO.builder().
                applicationId(onlineOfferResponseDTO.getAppId()).
                requestAmount(onlineOfferResponseDTO.getReqAm()).
                totalAmount(onlineOfferResponseDTO.getTotal()).
                term(onlineOfferResponseDTO.getTerm()).
                monthlyPayment(onlineOfferResponseDTO.getMonthlyPay()).
                build();
    }
}
