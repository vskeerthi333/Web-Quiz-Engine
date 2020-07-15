package engine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
interface UserRepository extends CrudRepository<User, String> {
}