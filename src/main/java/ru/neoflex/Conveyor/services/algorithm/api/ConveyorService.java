package ru.neoflex.Conveyor.services.algorithm.api;

import org.springframework.validation.BindingResult;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

import java.util.List;

public interface ConveyorService {
    List<LoanOfferDTO> getAllOffers(LoanApplicationRequestDTO applicationRequestDTO, BindingResult bindingResult);
}
