package com.example.pidev.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", text='" + text + '\'' +
                ", correct_option='" + correct_option + '\'' +
                ", option_id=" + option.getOption_id() +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long question_id;
    private String text;



    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrect_option() {
        return correct_option;
    }

    public void setCorrect_option(String correct_option) {
        this.correct_option = correct_option;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    private String correct_option;

    // relation one to many question et option.
    @ManyToOne
    @JoinColumn(name="option_id")
    private Option option;

    // relation many to one question et test.
    @OneToMany(mappedBy = "question")
    private List<Test> tests;
}
