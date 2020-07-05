package com.quizEngine.webQuiz;

import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class QuizHandler {

	private List<Quiz> quizzes;
	private static long id = 1;

	public QuizHandler() {
		quizzes = new ArrayList<Quiz>();
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public Quiz addQuiz(Quiz quiz) {
		quiz.setId(id++);
		quizzes.add(quiz);
		return quiz;
	}

	public Quiz getQuiz(long id) {
		return quizzes.get((int)id-1);
	}

	public long lastQuizId() {
		return id - 1;
	}

}