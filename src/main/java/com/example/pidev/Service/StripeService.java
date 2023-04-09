package com.example.pidev.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeService {
    @Value("sk_test_51Mu24hGSJqmPp6jfDiW5w3z6rGjdtF47gbo7FapMPnsa0CTWxwL9zmEgZMjaRIGN9mb45phz94zwy5nc6RICXBvr00SmXSIWrj")
    private String apiKey;

    public void chargeCreditCard(String token, Double amount, String currency) throws StripeException {

        Stripe.apiKey = apiKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", Math.round(amount * 100));
        params.put("currency", currency);
        params.put("source", token);
        params.put("description", "Payment for : ...");

        Charge charge = Charge.create(params);
    }
}
