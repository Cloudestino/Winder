package com.example.pidev.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Test implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long test_id;
    private String name;
    private String description;
    private String stack;
    private String level;



    //relation ManyToOne test et question.
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;


    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test_id=" + test_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stack='" + stack + '\'' +
                ", level='" + level + '\'' +
                ", question=" + question.getQuestion_id() +
                '}';
    }

}
