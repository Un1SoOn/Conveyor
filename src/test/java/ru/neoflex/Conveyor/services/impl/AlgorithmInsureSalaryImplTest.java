package ru.neoflex.Conveyor.services.impl;

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
    private final AlgorithmInsureSalaryImpl algorithmInsureSalary;

    @Autowired
    public AlgorithmInsureSalaryImplTest(AlgorithmInsureSalaryImpl algorithmInsureSalary) {
        this.algorithmInsureSalary = algorithmInsureSalary;
    }

    @Test
    public void offerShouldBeNotNull() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(LoanApplicationRequestDTO.
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

        Assert.isTrue(loanOfferDTO.getRate().doubleValue() == 0.12, "Rate is not correct");
    }

    @Test
    public void checkInsurance() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(LoanApplicationRequestDTO.
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

        Assert.isTrue(loanOfferDTO.getIsInsuranceEnabled(), "IsInsuranceEnabled should be true");
    }

    @Test
    public void checkSalaryClient() {
        LoanOfferDTO loanOfferDTO = algorithmInsureSalary.calcOffer(LoanApplicationRequestDTO.
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
