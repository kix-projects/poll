package kz.poll.data.repo;

import kz.poll.data.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QARepository extends MongoRepository<Question, String>,
		PagingAndSortingRepository<Question, String> {
	Question findBySubject(String subject);

	Question findById(String id);
}
