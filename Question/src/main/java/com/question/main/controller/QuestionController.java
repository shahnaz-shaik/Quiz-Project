package com.question.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.question.main.entity.Question;
import com.question.main.entity.QuestionWrapper;
import com.question.main.entity.Response;
import com.question.main.service.QuestionService;

@RestController
@RequestMapping("question")

public class QuestionController {
	@Autowired
	QuestionService questionService;
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions(){
		return questionService.getAllQuestions();
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		
		return questionService.addQuestion( question);
	}
	
	@PostMapping("addmany")
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions) {
	    try {
	        for (Question question : questions) {
	            questionService.addQuestion(question);
	        }
	        return ResponseEntity.ok("Questions added successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error adding questions: " + e.getMessage());
	    }
	}

	
	@PutMapping("update")
	public String updateQuestion(@RequestBody Question question){
		
		return questionService.updateQuestion(question);
	}
//	
//	@PutMapping("update/{id}")
//	public String updateCorrectAns(@PathVariable int id,@RequestBody String correctans){
//		
//		return questionService.updateCorrectAns(id,correctans);
//	}

	@DeleteMapping("delete/{id}")
	public String deleteQuestion( @PathVariable int id){
		return questionService.deleteQuestionById(id);
	}
	
	
	@GetMapping("subtopic/{subtopic}")
	public List<Question> getQuestionsBySubtopic(@PathVariable String subtopic){
		return questionService.getQuestionsBySubtopic(subtopic);
	}
	@GetMapping("level/{level}")
	public List<Question> getQuestionsByDifficultyLevel(@PathVariable String level){
		return questionService.getQuestionsByDifficultyLevel(level);
	}
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String subtopic , @RequestParam int numQueH, @RequestParam int numQueM, @RequestParam int numQueE, @RequestParam String title){
		return questionService.getQuestionsForQuiz(subtopic,numQueH,numQueM,numQueE,title);
	}
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
	return questionService.getQuestionsFromId(questionIds);
	}
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
		
	}
	@GetMapping("scoresBySubtopic")
	public ResponseEntity<Map<String, Integer>> getScoresBySubtopic(@RequestBody List<Response> responses) {
	    return questionService.getScoresBySubtopic(responses);
	}

	
}
