package com.example.pidev.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long option_id;
    private String text;

    public Long getOption_id() {
        return option_id;
    }

    public void setOption_id(Long option_id) {
        this.option_id = option_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Option{" +
                "option_id=" + option_id +
                ", text='" + text + '\'' +
                '}';
    }

    // relation many to one option et question.
    @OneToMany(mappedBy = "option")
    private List<Question> questions;

}
