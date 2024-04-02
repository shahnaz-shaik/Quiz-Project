package com.quiz.main.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.main.entity.QuestionWrapper;
import com.quiz.main.entity.Response;

@FeignClient("QUESTION")
public interface QuizInterface {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String subtopic , @RequestParam int numQueH, @RequestParam int numQueM, @RequestParam int numQueE, @RequestParam String title);
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
	@GetMapping("question/scoresBySubtopic")
	public ResponseEntity<Map<String, Integer>> getScoresBySubtopic(@RequestBody List<Response> responses) ;

}
