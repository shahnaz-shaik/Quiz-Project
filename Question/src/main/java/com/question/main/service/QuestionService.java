package com.question.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.question.main.dao.QuestionDao;
import com.question.main.entity.Question;
import com.question.main.entity.QuestionWrapper;
import com.question.main.entity.Response;

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

	public  ResponseEntity<List<Integer>> getQuestionsForQuiz(String subtopic, int numQueH, int numQueM, int numQueE,String title) {
		List<Integer> questions = questionDao.findRandomQuestionsBySubtopicAndLevel(subtopic, numQueH, numQueM, numQueE);
		return new ResponseEntity<>(questions,HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers =new ArrayList<>();
		List<Question> questions =new ArrayList<>();
		for(int id : questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question question: questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestion(question.getQuestion());
			wrapper.setSubtopic(question.getSubtopic());
			wrapper.setDifficultylevel(question.getDifficultylevel());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrapper.setQuestiontype(question.getQuestiontype());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right=0;
		for(Response response : responses) {
			Question question = questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getCorrectans()))
					right++;	
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
	public ResponseEntity<Map<String, Integer>> getScoresBySubtopic(List<Response> responses) {
        Map<String, Integer> scoresBySubtopic = new HashMap<>();

        for (Response response : responses) {
            int questionId = response.getId();
            String userResponse = response.getResponse();

            Optional<Question> optionalQuestion = questionDao.findById(questionId);
            if (optionalQuestion.isPresent()) {
                Question question = optionalQuestion.get();
                String subtopic = question.getSubtopic();
                String correctAnswer = question.getCorrectans();

                if (userResponse.equals(correctAnswer)) {
                    scoresBySubtopic.put(subtopic, scoresBySubtopic.getOrDefault(subtopic, 0) + 1);
                }
            }
        }

        return new ResponseEntity<>(scoresBySubtopic, HttpStatus.OK);
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
