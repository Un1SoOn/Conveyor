package ru.neoflex.Conveyor.services.algorithm.api;

import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

public interface AlgorithmInsureSalary {
    LoanOfferDTO calcOffer(LoanApplicationRequestDTO applicationRequestDTO);
}
