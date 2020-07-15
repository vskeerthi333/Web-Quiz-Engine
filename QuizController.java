package engine;

import org.springframework.security.core.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.*;
import org.springframework.http.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class QuizController {

	@Autowired private QuizHandler quizHandler;
	@Autowired private UserHandler userHadler;
	@Autowired private Result result;
	@Autowired private PasswordEncoder passwordEncoder;

	public QuizController() {		
	}

	@GetMapping("/quizzes")
	public List<Quiz> getAllQuizzes() {
		return quizHandler.getQuizzes();
	}

	@GetMapping("/quizzes/{id}")
	public Quiz getQuizById(@PathVariable long id) {
		Quiz quiz = quizHandler.getQuiz(id);
		if (quiz != null) {
			return quiz;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
	}

	@DeleteMapping("/quizzes/{id}")
	public ResponseEntity deleteQuiz(@PathVariable long id, Authentication authentication) {
		String userName = authentication.getName();
		Quiz quiz = quizHandler.getQuiz(id);
		if (quiz == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		if (quizHandler.deleteQuiz(quiz, userName))
				return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity(HttpStatus.FORBIDDEN);


	}

	@PostMapping("/register")
	public ResponseEntity registerUser(@Valid @RequestBody User user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		if (userHadler.addUserCredentials(user)) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/quizzes/{id}/solve")
	public Result validateQuiz(@PathVariable long id, @RequestBody Answer answer) {
		Quiz quiz = quizHandler.getQuiz(id);
		if (quiz != null) {
			boolean success = quizHandler.validateAnswer(quiz.getAnswer(), answer.getAnswer());
			result.setSuccess(success);
			if (success) { 
				result.setFeedback("Congratualations, you're right!"); 
			} 
			else { 
				result.setFeedback("Wrong answer! Please, try again."); 
			}
			return result;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");	
	}

	@PostMapping("/quizzes")
	public Quiz createQuiz(@Valid @RequestBody Quiz quiz, Authentication authentication) {
		String userName = authentication.getName();
		quiz.setUserName(userName);
		return quizHandler.addQuiz(quiz);
	}
}



