package engine;

import java.util.*;
import java.lang.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;

@Component
public class QuizHandler {

	@Autowired
	private QuizRepository quizRepository;

	public QuizHandler() {
	}

	public List<Quiz> getQuizzes() {
		List<Quiz> quizzes = new ArrayList<Quiz>();
		quizRepository.findAll().forEach(quizzes::add);
		return quizzes;
	}

	public Quiz addQuiz(Quiz quiz) {
		quizRepository.save(quiz);
		return quiz;
	}

	public Quiz getQuiz(Long id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		if (quiz.isPresent()) 
			return quiz.get();
		return null;
	}

	public boolean validateAnswer( int[] orgAnswer, int[] answer) { 
		try {
			if ( orgAnswer == null && (answer == null || answer.length == 0) ) 
				return true;
			if (orgAnswer.length != 0 && (answer == null || answer.length == 0))
				return false;
			if ( orgAnswer.length != answer.length)
				return false;
			for (int i = 0; i < orgAnswer.length; i++) {
				if (answer[i] != orgAnswer[i])
					return false;
			}
		}
		catch (Exception e) {
			return false;
		}
		return true; 
	}

	public boolean deleteQuiz(Quiz quiz, String userName) {
		if (quiz.getUserName().equals(userName)) {
			quizRepository.delete(quiz);
			return true;
		}
		return false;
	}

}