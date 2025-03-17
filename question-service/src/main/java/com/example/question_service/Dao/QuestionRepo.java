package com.example.question_service.Dao;


import com.example.question_service.model.Question;
import com.example.question_service.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer>{

    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category=:category order by  random() LIMIT :noOfQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);


}

