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
import ru.neoflex.Conveyor.services.algorithm.api.OnlineOfferService;

import java.util.List;

/** Отправка всевозможных кредитных предложений по запросу*/
@RestController
@RequestMapping("/conveyor")
@Slf4j
@RequiredArgsConstructor
public class ConveyorController implements ConveyorAPI {
    private final ConveyorService conveyorServiceImpl;
    private final OnlineOfferService onlineOfferServiceImpl;

    /** Получение списка предложений по запросу клиента*/
    @Override
    @PostMapping("/offers")
    public List<LoanOfferDTO> offer (LoanApplicationRequestDTO applicationRequestDTO,
                                    BindingResult bindingResult) {

        validateRequest(bindingResult);

        return prepareResponse(applicationRequestDTO, bindingResult);
    }

    /** Получение предложения со стороннего сервиса*/
    @Override
    @PostMapping("/online-offer")
    public LoanOfferDTO onlineOffer() {
        LoanOfferDTO loanOfferDTO =  onlineOfferServiceImpl.onlineOffer();
        log.info("Loan offer: {}", loanOfferDTO);
        return loanOfferDTO;
    }

    /** Вывод полей с ошибками при оформлении запроса на кредит в логи*/
    private void validateRequest(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError error : fieldErrors) {
                log.error(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new IllegalArgumentException();
        }
    }

    /** Получение всех доступных кредитных предложений*/
    private List<LoanOfferDTO> prepareResponse(LoanApplicationRequestDTO applicationRequestDTO, BindingResult bindingResult) {

        log.info("Application request: {}", applicationRequestDTO);

        return conveyorServiceImpl.getAllOffers(applicationRequestDTO, bindingResult);
    }
}