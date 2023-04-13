package com.example.pidev.Service;

import com.example.pidev.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question getQuestionById(Long question_id);
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    void deleteQuestion(Long question_id);
}
