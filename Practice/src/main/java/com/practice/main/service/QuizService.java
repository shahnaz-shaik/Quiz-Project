package com.practice.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.main.dao.QuestionDao;
import com.practice.main.dao.QuizDao;
import com.practice.main.entity.Question;
import com.practice.main.entity.QuestionWrapper;
import com.practice.main.entity.Quiz;
import com.practice.main.entity.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<String> createQuiz(String subtopic, int numQue, String title) {
		List<Question> questions = questionDao.findRandomQuestionsBySubtopic(subtopic,numQue);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDb= quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(Question q : questionsFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getSubtopic(),q.getDifficultylevel(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestiontype());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<String> createQuizByDifficultyLevel(String subtopic, int numQueH, int numQueM, int numQueS,String title) {
		List<Question> questions = questionDao.findRandomQuestionsBySubtopicAndLevel(subtopic, numQueH, numQueM, numQueS);
		Quiz quiz =new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<Integer> submitQuiz(int id, List<Response> responses) {
		Optional<Quiz> quiz =quizDao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		int right=0;
		int i=0;
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getCorrectans()))
					right++;
			i++;
			
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
  
}
