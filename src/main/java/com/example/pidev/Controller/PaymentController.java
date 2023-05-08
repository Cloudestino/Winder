package com.example.pidev.Controller;

import com.example.pidev.Service.PaymentHistoryService;
import com.example.pidev.Service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/payment")
@RestController
@PropertySource("classpath:application.properties")
public class PaymentController {
    @Autowired
    private StripeService stripeService;
   // @Autowired
    private PaymentHistoryService paymentHistoryService;
        @PostMapping("/charge/{token}/{amount}/{currency}")
    public ResponseEntity<String> chargeCard(@PathVariable(name="token") String token,
                                             @PathVariable(name="amount") Double amount,
                                             @PathVariable(name="currency") String currency)
    {

        try {
            stripeService.chargeCreditCard(token, amount, currency);

            return ResponseEntity.ok("Payment processed successfully!");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment: " + e.getMessage());
        }
    }
}
