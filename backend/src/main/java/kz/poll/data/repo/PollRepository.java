package kz.poll.data.repo;

import kz.poll.data.model.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PollRepository extends MongoRepository<Poll, String>, 
							PagingAndSortingRepository<Poll, String> {
		Poll findBySubject(String subject);
		Poll findById(String id);
}
