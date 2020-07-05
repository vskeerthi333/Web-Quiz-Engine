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
	private Result result;

	@Autowired
	public QuizController(QuizHandler quizHandler, Result result) {
		this.quizHandler = quizHandler;
		this.result = result;
	}

	@GetMapping
	public List<Quiz> getAllQuizzes() {
		return quizHandler.getQuizzes();
	}

	@GetMapping("/{id}")
	public Quiz getQuizById(@PathVariable long id) {
		Quiz quiz;
		try {
			quiz = quizHandler.getQuiz(id);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}
		return quiz;
	}

	@PostMapping("/{id}/solve")
	public Result validateQuiz(@PathVariable long id, @RequestBody Answer answer) {
		Quiz quiz;
		try {
			quiz = quizHandler.getQuiz(id);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}

		boolean success = quiz.validateAnswer(answer.getAnswer());
		result.setSuccess(success);
		if (success) { 
			result.setFeedback("Congratualations, you're right!"); 
		} 
		else { 
			result.setFeedback("Wrong answer! Please, try again."); 
		}
		return result;
	}

	@PostMapping
	public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
		return quizHandler.addQuiz(quiz);
	}
}



