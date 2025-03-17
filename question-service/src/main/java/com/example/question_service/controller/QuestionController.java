package com.example.question_service.controller;


import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    Environment environment;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getAllquestion(){
        //return "Hi ,here is all question";
        return questionService.getAllquestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
       return questionService.getByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String>  addquestion(@RequestBody  Question question){
        return questionService.addquestion(question);
    }
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer noOfQuestions){
        return questionService.getQuestionForQuiz(category,noOfQuestions);
    }
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionIds);
    }
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
