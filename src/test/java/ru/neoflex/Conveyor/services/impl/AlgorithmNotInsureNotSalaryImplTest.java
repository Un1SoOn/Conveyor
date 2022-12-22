package ru.neoflex.Conveyor.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;
import ru.neoflex.Conveyor.dto.LoanApplicationRequestDTO;
import ru.neoflex.Conveyor.dto.LoanOfferDTO;
import ru.neoflex.Conveyor.services.algorithm.impl.AlgorithmNotInsureNotSalaryImpl;

import java.time.LocalDate;

@SpringBootTest
public class AlgorithmNotInsureNotSalaryTest {
    private final AlgorithmNotInsureNotSalaryImpl algorithmNotInsureNotSalary;

    @Autowired
    public AlgorithmNotInsureNotSalaryTest(AlgorithmNotInsureNotSalaryImpl algorithmNotInsureNotSalary) {
        this.algorithmNotInsureNotSalary = algorithmNotInsureNotSalary;
    }

    @Test
    public void offerShouldBeNotNull() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureNotSalary.calcOffer(LoanApplicationRequestDTO.
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
        LoanOfferDTO loanOfferDTO = algorithmNotInsureNotSalary.calcOffer(LoanApplicationRequestDTO.
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

        Assert.isTrue(loanOfferDTO.getRate().doubleValue() == 0.30, "Rate is not correct");
    }

    @Test
    public void checkInsurance() {
        LoanOfferDTO loanOfferDTO = algorithmNotInsureNotSalary.calcOffer(LoanApplicationRequestDTO.
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
        LoanOfferDTO loanOfferDTO = algorithmNotInsureNotSalary.calcOffer(LoanApplicationRequestDTO.
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

        Assert.isTrue(!loanOfferDTO.getIsSalaryClient(), "IsSalaryClient should be false");
    }
}
