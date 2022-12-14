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
import ru.neoflex.Conveyor.controllers.api.ConveyorAPI;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/conveyor")
@Slf4j
public class ConveyorController implements ConveyorAPI {
    @Override
    @PostMapping("/offers")
    public List<LoanOfferDTO> offer (@RequestBody @Valid LoanApplicationRequestDTO applicationRequestDTO,
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
        Random random = new Random();

        List<LoanOfferDTO> loanOfferDTOS = new ArrayList<>();
        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(5246267L).
                totalAmount(5547367L).
                term(19).
                monthlyPayment(BigDecimal.valueOf(1122623)).
                rate(BigDecimal.valueOf(11263576)).
                isInsuranceEnabled(false).
                isSalaryClient(false).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(525778567L).
                totalAmount(55476367L).
                term(14).
                monthlyPayment(BigDecimal.valueOf(112524523)).
                rate(BigDecimal.valueOf(1162456576)).
                isInsuranceEnabled(false).
                isSalaryClient(true).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(52526567L).
                totalAmount(736573737L).
                term(34).
                monthlyPayment(BigDecimal.valueOf(11265623)).
                rate(BigDecimal.valueOf(114145626)).
                isInsuranceEnabled(true).
                isSalaryClient(false).
                build());

        loanOfferDTOS.add(LoanOfferDTO.builder().
                applicationId(random.nextLong(0,10000)).
                requestAmount(1325778567L).
                totalAmount(4565476367L).
                term(14).
                monthlyPayment(BigDecimal.valueOf(1232524523)).
                rate(BigDecimal.valueOf(625656576)).
                isInsuranceEnabled(true).
                isSalaryClient(true).
                build());

        return loanOfferDTOS;
    }
}
