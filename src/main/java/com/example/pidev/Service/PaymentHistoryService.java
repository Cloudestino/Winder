package com.example.pidev.Service;

import com.example.pidev.Repository.PaymentHistoryRepository;
import com.example.pidev.entity.PaymentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentHistoryService {
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    public void savePaymentHistory(String token, Double amount, String currency) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setToken(token);
        paymentHistory.setAmount(amount);
        paymentHistory.setCurrency(currency);
        paymentHistory.setDate(new Date());

        paymentHistoryRepository.save(paymentHistory);
    }


    public List<PaymentHistory> getAllPaymentHistory() {
        return paymentHistoryRepository.findAll();
    }

}
