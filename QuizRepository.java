package com.quizEngine.webQuiz;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
interface QuizRepository extends CrudRepository<Quiz, Long> {
}