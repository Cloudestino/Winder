package com.example.pidev.Service;

import com.example.pidev.Repository.QuestionRepository;
import com.example.pidev.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceC implements QuestionService {
    @Autowired
    QuestionRepository questionRepository ;

    @Override
    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long question_id) {
        return questionRepository.findById(question_id).get();
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long question_id) {
        questionRepository.deleteById(question_id);
    }
}
