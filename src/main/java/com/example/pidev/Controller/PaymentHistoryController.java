package com.example.pidev.Controller;

import com.example.pidev.Service.PaymentHistoryService;
import com.example.pidev.entity.PaymentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment/history")
@CrossOrigin("*")
public class PaymentHistoryController {
    @Autowired
    private PaymentHistoryService paymentHistoryService;

    @PostMapping("/save/{token}/{amount}/{currency}")
    public ResponseEntity<String> savePaymentHistory(@PathVariable(name="token") String token,
                                                     @PathVariable(name="amount") Double amount,
                                                     @PathVariable(name="currency") String currency)
    {

        try {
            paymentHistoryService.savePaymentHistory(token, amount, currency);
            return ResponseEntity.ok("Payment history saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving payment history: " + e.getMessage());
        }
    }

    @GetMapping("/retrieveAllpayments")
    public ResponseEntity<List<PaymentHistory>> getAllPaymentHistory() {
        try {
            List<PaymentHistory> paymentHistories = paymentHistoryService.getAllPaymentHistory();
            return ResponseEntity.ok(paymentHistories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
