package ru.neoflex.Conveyor.controllers.api;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

import java.util.List;

/** Интерфейс, содержащий методы для работы ConveyorController*/
@RequestMapping("/conveyor")
public interface ConveyorAPI {
    @PostMapping("/offers")
    List<LoanOfferDTO> offer(@RequestBody @Valid LoanApplicationRequestDTO applicationRequestDTO,
                                    BindingResult bindingResult);


    @PostMapping("/online-offer")
    LoanOfferDTO onlineOffer();
}
