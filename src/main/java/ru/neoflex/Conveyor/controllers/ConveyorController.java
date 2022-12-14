package ru.neoflex.Conveyor.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/conveyor")
@Slf4j
public class ConveyorController {
    @PostMapping("/offers")
    public List<LoanOfferDTO> offer(@RequestBody @Valid LoanApplicationRequestDTO applicationRequestDTO,
                                    BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError error : fieldErrors) {
                log.error(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new IllegalArgumentException();

        }
        log.info("Application request{}", applicationRequestDTO);
        Random random = new Random();

        List<LoanOfferDTO> loanOfferDTOS = new ArrayList<>();
        loanOfferDTOS.add(new LoanOfferDTO(random.nextLong(1, 10000), 1000530L, 11636000L, 7,
                BigDecimal.valueOf(1123), BigDecimal.valueOf(3456), false, false));

        loanOfferDTOS.add(new LoanOfferDTO(random.nextLong(1, 10000), 10042500L, 12321000L, 23,
                BigDecimal.valueOf(11235672), BigDecimal.valueOf(341556), false, true));

        loanOfferDTOS.add(new LoanOfferDTO(random.nextLong(0, 10000), 10000L, 11000L, 7,
                BigDecimal.valueOf(1122623), BigDecimal.valueOf(37467456), true, false));

        loanOfferDTOS.add(new LoanOfferDTO(random.nextLong(0, 10000), 10005530L, 11123000L, 7,
                BigDecimal.valueOf(1141523), BigDecimal.valueOf(3441456), true, true));

        return loanOfferDTOS;
    }
}
