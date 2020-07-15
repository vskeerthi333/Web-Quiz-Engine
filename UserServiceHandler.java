package engine;

import java.util.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;

@Component
public class UserServiceHandler implements UserDetailsService {

	@Autowired
	private UserHandler userHandler;

	public UserServiceHandler() {
	}

	public UserDetails loadUserByUsername(String userName)  {
		User user = userHandler.getUserCredentials(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
}