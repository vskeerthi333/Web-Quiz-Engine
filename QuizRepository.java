package engine;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {
}