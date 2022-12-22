package ru.neoflex.Conveyor.services.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.impl.AlgorithmInsureSalaryImpl;

import java.time.LocalDate;

@SpringBootTest
public class AlgorithmInsureSalaryImplTest {

    /** Тестируемый сервис расчета кредитного предолжения при наличии страховки и клиентства в банке*/
    @Autowired
    private final AlgorithmInsureSalaryImpl algorithmInsureSalary = new AlgorithmInsureSalaryImpl();

    /** Данные предполагаемого клиента, оформляющего кредит */
    private final LoanApplicationRequestDTO loanApplicationRequestDTO = LoanApplicationRequestDTO.
            builder().
            amount(150000L).
            term(12).
            firstName("Ivan").
            lastName("Mikhalev").
            middleName("Igorevich").
            email("PinMII@yandex.ru").
            birthdate(LocalDate.ofEpochDay(1990-01-01)).
            passportSeries("6578").
            passportNumber("638586").
            build();

    @Test
    @DisplayName("Тестирование предложение на null")
    public void offerShouldBeNotNull() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(loanApplicationRequestDTO);

        Assert.notNull(loanOfferDTO, "Offer is null");
    }

    @Test
    @DisplayName("Тестирование результата расчета итоговой ставки")
    public void checkRate() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(loanApplicationRequestDTO);

        Assert.isTrue(loanOfferDTO.getRate().doubleValue() == 0.12, "Rate is not correct");
    }

    @Test
    @DisplayName("Тестирование предложения на наличие страховки у оформляющего кредит")
    public void checkInsurance() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(loanApplicationRequestDTO);

        Assert.isTrue(loanOfferDTO.getIsInsuranceEnabled(), "IsInsuranceEnabled should be true");
    }

    @Test
    @DisplayName("Тестирование предложение на наличие клиентства в банке у оформляющего кредит")
    public void checkSalaryClient() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(loanApplicationRequestDTO);

        Assert.isTrue(loanOfferDTO.getIsSalaryClient(), "IsSalaryClient should be true");
    }
}
