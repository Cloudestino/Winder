package com.example.pidev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String skills;

    @Enumerated(EnumType.STRING)
    private Scope scope;
    private Date duration;
    private String experience;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
