package com.example.quiz_service.controller;


import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.QuizDto;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;
@PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody  QuizDto quizDto){
   // return new ResponseEntity<>("I am here", HttpStatus.OK) ;
return quizService.createQuiz(quizDto.getCategory(),quizDto.getNoOfQuestions(),quizDto.getTitle());
}
@GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer id){
     return  quizService.getQuizQuestions(id);
}
@PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
    return quizService.calculateResult(id,response);
}
}





