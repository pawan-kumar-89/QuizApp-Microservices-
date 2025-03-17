package com.example.question_service.service;


import com.example.question_service.Dao.QuestionRepo;
import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionService questionService;
    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllquestions() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getByCategory(String category) {

        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addquestion(Question question) {
   questionRepo.save(question);
   try{
       return new ResponseEntity<>("success",HttpStatus.CREATED);
   } catch (Exception e) {
       e.printStackTrace();
   }
   return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer noOfQuestions) {
        List<Integer> questions=questionRepo.findRandomQuestionsByCategory(category,noOfQuestions);
   return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
   List<QuestionWrapper> wrappers=new ArrayList<>();
   List<Question> questions=new ArrayList<>();
   for(Integer id:questionIds){
       questions.add(questionRepo.findById(id).get());
   }
   for(Question ques:questions){
       QuestionWrapper wrap =new QuestionWrapper();
       wrap.setId(ques.getId());
       wrap.setQuestionTitle(ques.getQuestionTitle());
       wrap.setOption1(ques.getOption1());
       wrap.setOption2(ques.getOption2());
       wrap.setOption3(ques.getOption3());
       wrap.setOption4(ques.getOption4());
       wrappers.add(wrap);
   }
   return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response res:responses){
            Question question=questionRepo.findById(res.getId()).get();
            if(res.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
