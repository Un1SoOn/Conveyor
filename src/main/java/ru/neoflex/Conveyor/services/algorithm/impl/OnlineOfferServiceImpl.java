package ru.neoflex.Conveyor.services.algorithm.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.dto.OnlineOfferResponseDTO;
import ru.neoflex.Conveyor.exception.IncorrectURLException;
import ru.neoflex.Conveyor.mapper.LoanOfferDTOMapper;
import ru.neoflex.Conveyor.services.algorithm.api.OnlineOfferService;

/** Реализация сервиса, получающего предложение со стороннего API*/

@Service
@RequiredArgsConstructor
public class OnlineOfferServiceImpl implements OnlineOfferService {
    /** Маппер*/
    private final RestTemplate restTemplate = new RestTemplate();
    /** URL стороннего API */
    @Value("${url.outside.api}")
    private String URL;
    private final LoanOfferDTOMapper loanOfferDTOMapper;

    /** Получение предложения со стороннего сервиса*/
    @Override
    public LoanOfferDTO onlineOffer() {
        OnlineOfferResponseDTO onlineOfferResponseDTO;
        try {
            onlineOfferResponseDTO = restTemplate.postForObject(URL, HttpEntity.EMPTY, OnlineOfferResponseDTO.class);
        } catch (Exception e){
            throw new IncorrectURLException();
        }

        return loanOfferDTOMapper.toLoanOfferDTO(onlineOfferResponseDTO);
    }
}
