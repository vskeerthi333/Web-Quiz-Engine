package com.quizEngine.webQuiz;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;


@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

	private QuizHandler quizHandler;

	@Autowired
	public QuizController(QuizHandler quizHandler) {
		this.quizHandler = quizHandler;
	}

	@GetMapping
	public List<Quiz> getAllQuizzes() {
		return quizHandler.getQuizzes();
	}

	@GetMapping("/{id}")
	public Quiz getQuizById(@PathVariable long id) {
		if (id <= quizHandler.lastQuizId())
			return quizHandler.getQuiz(id-1);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
	}

	@PostMapping("/{id}/solve")
	public Result validateQuiz(@PathVariable long id, @RequestBody String answer) {
		if (id > quizHandler.lastQuizId()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

		Quiz quiz = quizHandler.getQuiz(id-1);
		boolean success = quiz.validateAnswer(answer);
		if (success) { return new Result(success, "Congratualations, you're right!"); } 
		else { return new Result(success, "Wrong answer! Please, try again."); }
	}

	@PostMapping
	public Quiz createQuiz(@RequestBody Quiz quiz) {
		return quizHandler.addQuiz(quiz);
	}
}



