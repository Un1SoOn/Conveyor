package ru.neoflex.Conveyor.dto;

import java.math.BigDecimal;

/**
 * Элемент списка кредитного предложения
 * */
public class LoanOfferDTO {
    /** Идентификатор*/
    private Long applicationId;
    /** Сумма из LoanApplicationRequestDTO(amount), запрошенная клиентом*/
    private Long requestAmount;
    /** Сумма рассчитанная с учетом страховки, логики расчета*/
    private Long totalAmount;
    /** Количество месяцев из LoanApplicationRequestDTO(term), указанное клиентом*/
    private Integer term;
    /** Ежемесячный платеж*/
    private BigDecimal monthlyPayment;
    /** Годовая ставка клиента*/
    private BigDecimal rate;
    /** Если страховка имеется - true, не имеется - false*/
    private Boolean isInsuranceEnabled;
    /** Является зарплатным клиентом банка - true, не является - false*/
    private Boolean isSalaryClient;

    public LoanOfferDTO(){
    }

    public LoanOfferDTO(Long applicationId, Long requestAmount, Long totalAmount,
                        Integer term, BigDecimal monthlyPayment, BigDecimal rate,
                        Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        this.applicationId = applicationId;
        this.requestAmount = requestAmount;
        this.totalAmount = totalAmount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Long requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getInsuranceEnabled() {
        return isInsuranceEnabled;
    }

    public void setInsuranceEnabled(Boolean insuranceEnabled) {
        isInsuranceEnabled = insuranceEnabled;
    }

    public Boolean getSalaryClient() {
        return isSalaryClient;
    }

    public void setSalaryClient(Boolean salaryClient) {
        isSalaryClient = salaryClient;
    }

    @Override
    public String toString() {
        return "LoanOfferDTO{" +
                "applicationId=" + applicationId +
                ", requestAmount=" + requestAmount +
                ", totalAmount=" + totalAmount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                '}';
    }
}
