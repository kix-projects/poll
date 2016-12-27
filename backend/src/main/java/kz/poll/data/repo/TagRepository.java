package kz.poll.data.repo;

import kz.poll.data.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends MongoRepository<Tag, String>,
		PagingAndSortingRepository<Tag, String> {
	Tag findByName(String name);

	Tag findById(String id);
}
