package com.example.pidev.Service;

import com.example.pidev.Repository.TestRepository;
import com.example.pidev.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TestServiceC implements  TestService {
    private final TestRepository testRepository;
    @Autowired
    public TestServiceC(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test createTest(Test test){
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public void deleteTest(Long test_id) {

        testRepository.deleteById(test_id);
    }

    public List<Test> getAllTests() {

        return (List<Test>) testRepository.findAll();
    }

    public Test getTestById(Long test_id) {
        return testRepository.findById(test_id).get();

    }
}
