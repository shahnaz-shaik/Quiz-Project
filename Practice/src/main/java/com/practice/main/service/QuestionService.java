package com.practice.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.practice.main.dao.QuestionDao;
import com.practice.main.entity.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
	}

	public List<Question> getQuestionsBySubtopic(String subtopic) {
		
		return questionDao.findBySubtopic(subtopic);
	}

	public List<Question> getQuestionsByDifficultyLevel(String level) {
	
		return questionDao.findBydifficultylevel(level);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
		questionDao.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failure",HttpStatus.BAD_REQUEST);
	}

	public String updateQuestion(Question question) {
		
	            questionDao.save(question);
	           return "success";
	}

	public String deleteQuestionById(int id) {
	questionDao.deleteById(id);
	return "success";
	}

//	public String updateCorrectAns(int id, String correctans) {
//	    Optional<Question> optionalQuestion = questionDao.findById(id);
//	    if (optionalQuestion.isPresent()) {
//	        Question question = optionalQuestion.get();
//	        question.setCorrectans(correctans);
//	        questionDao.save(question);
//	        return "Correct answer updated successfully";
//	    } else {
//	        return "Question not found";
//	    }
//	}

  
}
