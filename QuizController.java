package engine;

import org.springframework.security.core.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.*;
import org.springframework.http.*;
import org.springframework.data.domain.*;
import javax.validation.Valid;
import java.time.LocalDateTime;  
import java.util.*;


@RestController
@RequestMapping("/api")
public class QuizController {

	@Autowired private QuizHandler quizHandler;
	@Autowired private UserHandler userHadler;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private Result result;

	public QuizController() {		
	}

	@GetMapping("/quizzes")
	public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
		return quizHandler.getQuizzes(page, pageSize);
	}

	@GetMapping("/quizzes/completed")
	public Page<CompletedQuiz> getCompletedQuizzes(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Authentication authentication) {
		return userHadler.getCompletedQuizzes(authentication.getName(), page, pageSize);
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

	@PostMapping("/quizzes")
	public Quiz createQuiz(@Valid @RequestBody Quiz quiz, Authentication authentication) {
		String userName = authentication.getName();
		quiz.setUserName(userName);
		return quizHandler.addQuiz(quiz);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleException() {
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/quizzes/{id}/solve")
	public Result validateQuiz(@PathVariable long id, @RequestBody Answer answer, Authentication authentication) {

		Quiz quiz = quizHandler.getQuiz(id);
		LocalDateTime completionTime = LocalDateTime.now();

		if (quiz != null) {
			boolean success = quizHandler.validateAnswer(quiz.getAnswer(), answer.getAnswer());
			result.setSuccess(success);

			if (success) {
				userHadler.quizCompletion(authentication.getName(), id, completionTime);
				result.setFeedback("Congratualations, you're right!"); 
			}
		
			else result.setFeedback("Wrong answer! Please, try again."); 
			
			return result;
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");	
	}

}



