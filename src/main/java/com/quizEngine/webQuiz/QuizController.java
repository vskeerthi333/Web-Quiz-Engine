package com.quizEngine.webQuiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

	private Quiz quiz = null;

	public QuizController() {
		quiz = new Quiz("sampleQuiz", "who invented C language ?", new String[] {"Alan Turing", "Claude Shannon", "Dennis Ritchie", "Tim Berners-Lee"}, "2"); 
	}

	@GetMapping("/api/quiz")
	public Quiz getQuiz() {
		return quiz;
	}

	@PostMapping("/api/quiz")
	public Result verfiyResult(@RequestBody String answer) {
		boolean success = ("2".equals(answer));

		if (success) { return new Result(success, "Congratualations, you're right!"); } 
		else { return new Result(success, "Wrong answer! Please, try again."); }
	}
}



