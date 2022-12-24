package ru.neoflex.Conveyor.services.algorithm.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.dto.OnlineOfferResponseDTO;
import ru.neoflex.Conveyor.services.algorithm.api.OnlineOfferService;

/** Реализация сервиса, получающего предложение со стороннего API*/
@Service
public class OnlineOfferServiceImpl implements OnlineOfferService {
    private final RestTemplate restTemplate = new RestTemplate();
    /** URL стороннего API */
    private static final String URL = "https://api.fake.rest/33c24b27-8563-44f7-a110-a879538854cd/online-offer";
    /** Полученное предложение*/
    private OnlineOfferResponseDTO onlineOfferResponseDTO;

    @Override
    public LoanOfferDTO remappingOffer() {
        onlineOfferResponseDTO = restTemplate.postForObject(URL, onlineOfferResponseDTO,OnlineOfferResponseDTO.class);

        return LoanOfferDTO.builder().
                applicationId(onlineOfferResponseDTO.getAppId()).
                requestAmount(onlineOfferResponseDTO.getReqAm()).
                totalAmount(onlineOfferResponseDTO.getTotal()).
                term(onlineOfferResponseDTO.getTerm()).
                monthlyPayment(onlineOfferResponseDTO.getMonthlyPay()).
                build();
    }
}
