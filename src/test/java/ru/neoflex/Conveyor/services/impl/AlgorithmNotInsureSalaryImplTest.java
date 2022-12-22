package ru.neoflex.Conveyor.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.impl.AlgorithmNotInsureSalaryImpl;

import java.time.LocalDate;

@SpringBootTest
public class AlgorithmNotInsureSalaryImplTest {
    private final AlgorithmNotInsureSalaryImpl algorithmNotInsureSalary;

    @Autowired
    public AlgorithmNotInsureSalaryImplTest(AlgorithmNotInsureSalaryImpl algorithmNotInsureSalary) {
        this.algorithmNotInsureSalary = algorithmNotInsureSalary;
    }

    @Test
    public void offerShouldBeNotNull() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
                build());

        Assert.notNull(loanOfferDTO, "Offer is null");
    }

    @Test
    public void checkRate() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
                build());

        Assert.isTrue(loanOfferDTO.getRate().doubleValue() == 0.28, "Rate is not correct");
    }

    @Test
    public void checkInsurance() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
                build());

        Assert.isTrue(!loanOfferDTO.getIsInsuranceEnabled(), "IsInsuranceEnabled should be false");
    }

    @Test
    public void checkSalaryClient() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
                build());

        Assert.isTrue(loanOfferDTO.getIsSalaryClient(), "IsSalaryClient should be true");
    }
}
