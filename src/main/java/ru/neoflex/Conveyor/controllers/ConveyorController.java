package ru.neoflex.Conveyor.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.Conveyor.controllers.api.ConveyorAPI;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.ConveyorService;

import java.util.List;

@RestController
@RequestMapping("/conveyor")
@Slf4j
public class ConveyorController implements ConveyorAPI {

    private ConveyorService conveyorService;

    @Autowired
    public ConveyorController(ConveyorService conveyorService) {
        this.conveyorService = conveyorService;
    }

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

        return conveyorService.prepareResponse(applicationRequestDTO, bindingResult);
    }
}
