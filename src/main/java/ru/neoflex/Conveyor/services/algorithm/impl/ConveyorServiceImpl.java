package ru.neoflex.Conveyor.services.algorithm.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.*;

import java.util.List;

/**
 * Реализация сервиса по рассчету кредитных предложений
 */
@Service
@RequiredArgsConstructor
public class ConveyorServiceImpl implements ConveyorService {
    /***/
    private final AlgorithmNotInsureNotSalary algorithmNotInsureNotSalary;
    private final AlgorithmNotInsureSalary algorithmNotInsureSalary;
    private final AlgorithmInsureNotSalary algorithmInsureNotSalary;
    private final AlgorithmInsureSalary algorithmInsureSalary;

    public List<LoanOfferDTO> getAllOffers(LoanApplicationRequestDTO applicationRequestDTO,
                                           BindingResult bindingResult) {
        LoanOfferDTO loanOfferDTOFirst = algorithmNotInsureNotSalary.calcOffer(applicationRequestDTO);

        LoanOfferDTO loanOfferDTOSecond = algorithmNotInsureSalary.calcOffer(applicationRequestDTO);
        LoanOfferDTO loanOfferDTOThird = algorithmInsureNotSalary.calcOffer(applicationRequestDTO);
        LoanOfferDTO loanOfferDTOFourth = algorithmInsureSalary.calcOffer(applicationRequestDTO);

        return List.of(loanOfferDTOFirst, loanOfferDTOSecond, loanOfferDTOThird, loanOfferDTOFourth);
    }
}
