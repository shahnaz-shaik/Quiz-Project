package com.quiz.main.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.quiz.main.dao.QuizDao;
import com.quiz.main.entity.QuestionWrapper;
import com.quiz.main.entity.Quiz;
import com.quiz.main.entity.Response;
import com.quiz.main.feign.QuizInterface;


@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String subtopic, int numQueH, int numQueM, int numQueE, String title){
	List<Integer> questions = quizInterface.getQuestionsForQuiz(subtopic, numQueH, numQueM, numQueE,title).getBody();
	Quiz quiz =new Quiz();
	quiz.setTitle(title);
	quiz.setQuestionIds(questions);
	quizDao.save(quiz);
	return new ResponseEntity<>("success",HttpStatus.CREATED);
	
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
		return questions;
}
//
//	public ResponseEntity<String> createQuizByDifficultyLevel(String subtopic, int numQueH, int numQueM, int numQueS,String title) {
//////		List<Question> questions = questionDao.findRandomQuestionsBySubtopicAndLevel(subtopic, numQueH, numQueM, numQueS);
//	Quiz quiz =new Quiz();
//////		quiz.setTitle(title);
//////		quiz.setQuestions(questions);
//		quizDao.save(quiz);
//		return new ResponseEntity<>("success",HttpStatus.CREATED);
////	}

	public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
	ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}

	 
  
//	}
}
