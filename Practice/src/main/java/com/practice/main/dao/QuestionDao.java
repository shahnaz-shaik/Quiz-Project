package com.practice.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.main.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
 List<Question> findBySubtopic(String subtopic);
 List<Question> findBydifficultylevel(String level);

 @Query(value = "SELECT * FROM question q WHERE q.subtopic = :subtopic ORDER BY RAND() LIMIT :numQue", nativeQuery = true)
 List<Question> findRandomQuestionsBySubtopic(@Param("subtopic") String subtopic, @Param("numQue") int numQue);

 @Query(value = 
		    "SELECT * FROM ( " +
		    "  (SELECT * FROM question q WHERE q.subtopic = :subtopic AND q.difficultylevel = 'hard' ORDER BY RAND() LIMIT :numQueH) " +
		    "  UNION ALL " +
		    "  (SELECT * FROM question q WHERE q.subtopic = :subtopic AND q.difficultylevel = 'medium' ORDER BY RAND() LIMIT :numQueM) " +
		    "  UNION ALL " +
		    "  (SELECT * FROM question q WHERE q.subtopic = :subtopic AND q.difficultylevel = 'easy' ORDER BY RAND() LIMIT :numQueS) " +
		    ") AS combined " +
		    "ORDER BY RAND()", 
		    nativeQuery = true)
		List<Question> findRandomQuestionsBySubtopicAndLevel(@Param("subtopic") String subtopic,@Param("numQueH") int numQueH,@Param("numQueM") int numQueM,@Param("numQueS") int numQueS);
}
