package ru.neoflex.Conveyor.services.algorithm.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.api.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса по рассчету кредитных предложений
 */
@Service
@RequiredArgsConstructor
public class ConveyorServiceImpl implements ConveyorService {
    /**Список, содержащий расчеты четырех предложений*/
    private final List<Algorithm> algorithmList;

    public List<LoanOfferDTO> getAllOffers(LoanApplicationRequestDTO applicationRequestDTO,
                                           BindingResult bindingResult) {

        return algorithmList.stream().map(algorithm -> algorithm.calcOffer(applicationRequestDTO)).sorted(new Comparator<LoanOfferDTO>() {
            @Override
            public int compare(LoanOfferDTO o1, LoanOfferDTO o2) {
                return Double.compare(o1.getRate().doubleValue(), o2.getRate().doubleValue());
            }
        }).collect(Collectors.toList());
    }
}
