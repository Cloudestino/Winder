package com.example.pidev.Controller;

import com.example.pidev.Service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment")
@RestController
public class PaymentController {
    @Autowired
    private StripeService stripeService;
    @PostMapping("/charge")
    public ResponseEntity<String> chargeCard(@RequestParam("token") String token,
                                             @RequestParam("amount") Double amount,
                                             @RequestParam("currency") String currency)
    {

        try {
            stripeService.chargeCreditCard(token, amount, currency);

            return ResponseEntity.ok("Payment processed successfully!");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }
    }
}
