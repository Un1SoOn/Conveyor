package ru.neoflex.Conveyor.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.Conveyor.controllers.api.ConveyorAPI;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.ConveyorService;

import java.util.List;

/** Отправка всевозможных кредитных предложений по запросу*/
@RestController
@RequestMapping("/conveyor")
@Slf4j
@RequiredArgsConstructor
public class ConveyorController implements ConveyorAPI {
    private final ConveyorService conveyorServiceImpl;

    @Override
    @PostMapping("/offers")
    public List<LoanOfferDTO> offer (LoanApplicationRequestDTO applicationRequestDTO,
                                    BindingResult bindingResult) {

        validateRequest(bindingResult);

        return prepareResponse(applicationRequestDTO, bindingResult);
    }

    private void validateRequest(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError error : fieldErrors) {
                log.error(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new IllegalArgumentException();
        }
    }

    private List<LoanOfferDTO> prepareResponse(LoanApplicationRequestDTO applicationRequestDTO, BindingResult bindingResult) {

        log.info("Application request{}", applicationRequestDTO);

        return conveyorServiceImpl.getAllOffers(applicationRequestDTO, bindingResult);
    }
}