package ru.neoflex.Conveyor.services.algorithm.api;

import org.springframework.validation.BindingResult;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.dto.OnlineOfferResponseDTO;

public interface OnlineOfferService {
    LoanOfferDTO remappingOffer();
}
