package engine;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import java.time.LocalDateTime;  
import java.util.regex.*;
import java.util.*;

@Component
public class UserHandler {

	private static final String regExpression = "^(.+)@(.+).(.+)$";
	private Pattern pattern = Pattern.compile(regExpression);

	public UserHandler() {
	}
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompletedQuizRepository completedQuizRepository;

	public boolean addUserCredentials(User user) {
		if (userRepository.existsById(user.getEmail())) {
			return false;
		}
	
		Matcher matcher = pattern.matcher(user.getEmail());
		if (matcher.matches()) {
			userRepository.save(user);
			return true;
		}
		
		return false;
	}

	public void quizCompletion(String userName, long id, LocalDateTime completionTime) {
		completedQuizRepository.addCompletedQuiz(userName, id, completionTime);
	}

	public User getUserCredentialsById(String userName) {
		Optional<User> userCredentials = userRepository.findById(userName);
		if (userCredentials.isPresent())
			return userCredentials.get();
		return null;
	}

	public Page<CompletedQuiz> getCompletedQuizzes(String userName, int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		return completedQuizRepository.findAllUsersWithPagination(userName, paging);
	}
	
}