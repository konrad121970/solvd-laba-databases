package com.solvd.laba.domain.contract;

import java.sql.Date;
import java.util.List;

public class MonthlyPayment {
    private Long id;
    private Double amount;
    private Date payment_date;
    private List<BonusPayment> bonusPaymentList;
}
