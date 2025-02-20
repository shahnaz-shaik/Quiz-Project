package com.quiz.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.main.entity.QuestionWrapper;
import com.quiz.main.entity.Response;
import com.quiz.main.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
		@PostMapping("create")
		public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
			return quizService.createQuiz(quizDto.getSubtopic(),quizDto.getNumQueH(),quizDto.getNumQueM(),quizDto.getNumQueE(),quizDto.getTitle());
		}
		@GetMapping("get/{id}")
		public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
			return quizService.getQuizQuestions(id);
		
		}
//		@PostMapping("/createbylevel")
//		public ResponseEntity<String> createQuizByDifficultyLevel(@RequestParam String subtopic, @RequestParam int numQueH, @RequestParam int numQueM, @RequestParam int numQueS, @RequestParam String title){
//			return quizService.createQuizByDifficultyLevel(subtopic,numQueH,numQueM,numQueS,title);
//		}

	@PostMapping("submit/{id}")
		public ResponseEntity<Integer> submitQUiz(@PathVariable int id,@RequestBody List<Response> responses){
		return quizService.submitQuiz(id,responses);
		}
//}
}