package com.example.pidev.Controller;

import com.example.pidev.Service.TestServiceC;
import com.example.pidev.entity.Test;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tests")
public class TestRestController {

    TestServiceC testService;

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

    // http://localhost:8089/pidev/tests/remove-test/1
    @DeleteMapping("/remove-test/{test-id}")
    public void removeTest(@PathVariable("test-id") Long testId) {
        testService.deleteTest(testId);
    }

    // http://localhost:8089/pidev/tests/update-test
    @PutMapping("/update-test")
    public Test updateTest(@RequestBody Test test) {
        Test test1 = testService.updateTest(test);
        return test1;
    }






















}
