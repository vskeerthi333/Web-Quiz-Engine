package engine;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Component;

@Component
interface UserRepository extends JpaRepository<User, String> {
}