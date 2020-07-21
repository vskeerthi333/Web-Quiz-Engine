package engine;

import org.springframework.transaction.annotation.*;
import java.time.LocalDateTime;  
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.*;

@Component
interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Long> {

	@Query(value = "SELECT * FROM COMPLETEDQUIZ WHERE EMAIL = ?1", nativeQuery = true)
	public Page<CompletedQuiz> findAllUsersWithPagination(String email, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO COMPLETEDQUIZ (EMAIL, ID, COMPLETEDAT) VALUES (?1, ?2, ?3)", nativeQuery = true)
	public void addCompletedQuiz(String userName, long quizId, LocalDateTime completionTime);

}