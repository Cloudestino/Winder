package com.example.pidev.Controller;

import com.example.pidev.Repository.QuestionRepository;
import com.example.pidev.Repository.TestRepository;
import com.example.pidev.Service.QuestionServiceC;
import com.example.pidev.Service.TestServiceC;
import com.example.pidev.entity.Question;
import com.example.pidev.entity.Test;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tests")
public class TestRestController {

    TestServiceC testService;
    QuestionRepository questionRepository ;
    TestRepository testRepository ;
    QuestionServiceC questionService;

    // http://localhost:8089/pidev/tests/retrieve-all-tests
    @GetMapping("/retrieve-all-tests")
    public List<Test> getTests() {
        List<Test> listTests = testService.getAllTests();
        return listTests;
    }


    // http://localhost:8089/pidev/tests/retrieve-test/8
    @GetMapping("/retrieve-test/{test-id}")
    public Test retrieveTest(@PathVariable("test-id") Long TestId) {
        return testService.getTestById(TestId);
    }


    // http://localhost:8089/pidev/tests/add-test
    @PostMapping("/add-test")
    public Test addTest(@RequestBody Test test) {
        Test test1 = testService.createTest(test);
        return test1;
    }
    // http://localhost:8089/pidev/tests/{test_id}/questions/{question_id}
    @PostMapping("/{test_id}/questions/{question_id}")
    public Test addQuestionToTest(@PathVariable("test_id") Long test_id, @PathVariable("question_id") Long question_id) {
        Test test = testRepository.findById(test_id).orElse(null);
        Question question = questionRepository.findById(question_id).orElseThrow(() -> new EntityNotFoundException("Entity with ID " + question_id + " not found"));
        test.getQuestions().add(question);
        return testRepository.save(test);
    }

    @DeleteMapping("/remove-test/{test-id}/aa")
    public void removeQuestionFromTest(@PathVariable("test_id") Long test_id, @PathVariable("question_id") Long question_id) {
        testService.deleteTest(test_id);
    }

    // http://localhost:8089/pidev/tests/remove-test/1
    @DeleteMapping("/remove-test/{test_id}")
    public void removeTest(@PathVariable("test_id") Long test_id) {
        testService.deleteTest(test_id);
    }

    // http://localhost:8089/pidev/tests/update-test
    @PutMapping("/update-test")
    public Test updateTest(@RequestBody Test test) {
        Test test1 = testService.updateTest(test);
        return test1;
    }

    // http://localhost:8089/pidev/tests/update-test/{test_id}/questions/{question_id}
    @PutMapping("/update-test/{test_id}/questions/{question_id}")
    public Test updateQuestionInTest(@PathVariable("test_id") Long test_id, @PathVariable("question_id") Long question_id) {
        Test test = testRepository.findById(test_id).orElse(null);
        Question question = questionRepository.findById(question_id).orElse(null);
        if (test.getQuestions().contains(question)) {
            test.getQuestions().remove(question);
            Question rand = changeQuestion();

            test.getQuestions().add(rand);
            Test test1 = testService.updateTest(test);
            return test1;
        }
        else {
            return testService.getTestById(test_id);
        }
    }

    public Question changeQuestion() {
        List<Question> listQuestions = questionService.getAllQuestions();
        Collections.shuffle(listQuestions);
        Question randQuestion = listQuestions.get(0);
        return randQuestion;
    }




















}
