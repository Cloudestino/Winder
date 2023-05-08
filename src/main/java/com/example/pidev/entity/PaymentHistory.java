package com.example.pidev.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String token;

    //@Column(nullable = false)
    private Double amount;

    //@Column(nullable = false)
    private String currency;

   // @Column(nullable = false)
    private Date date;
}
