package kz.poll.data.repo;

import kz.poll.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends MongoRepository<User, String>,
		PagingAndSortingRepository<User, String> {

	User findByUserName(String userName);
}
