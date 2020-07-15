package engine;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;
import java.util.regex.*;
import java.util.Optional;

@Component
public class UserHandler {

	private static final String regExpression = "^(.+)@(.+)$";
	private Pattern pattern = Pattern.compile(regExpression);

	public UserHandler() {
	}
	
	@Autowired
	private UserRepository userRepository;

	public User getUserCredentials(String email) {
		Optional<User> credentials = userRepository.findById(email);
		if (credentials.isPresent()) 
			return credentials.get();
		return null;
	}

	public boolean addUserCredentials(User user) {
		String userName = user.getEmail();
		Optional<User> credentials = userRepository.findById(userName);

		if (!credentials.isPresent()) {
			Matcher matcher = pattern.matcher(userName);
			if (matcher.matches()) {
				userRepository.save(user);
				return true;
			}
		}	
		return false;
	}

	public User getUserCredentialsById(String userName) {
		Optional<User> userCredentials = userRepository.findById(userName);
		if (userCredentials.isPresent()) {
			return userCredentials.get();
		}
		return null;
	}
}