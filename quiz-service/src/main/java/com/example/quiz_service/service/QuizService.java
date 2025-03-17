package com.example.quiz_service.service;


import com.example.quiz_service.Dao.QuizRepo;
import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
     @Autowired
     private QuizRepo quizRepo;
     @Autowired
     private QuizInterface quizInterface;
//     @Autowired
//     private QuestionRepo questionRepo;
     public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
List<Integer> questions=  quizInterface.getQuestionForQuiz(category,noOfQuestions).getBody();
   Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
             // here id is quiz id and we get question of the quiz
            Quiz quiz=quizRepo.findById(id).get();
            List<Integer> questionIds=quiz.getQuestionIds();
            ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionIds);
            return questions;
     }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
  ResponseEntity<Integer> score=quizInterface.getScore(response);
    return score;
    }
}
