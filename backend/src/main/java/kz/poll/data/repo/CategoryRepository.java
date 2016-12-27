package kz.poll.data.repo;

import kz.poll.data.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends MongoRepository<Category, String>,
		PagingAndSortingRepository<Category, String> {
	Category findByName(String name);

	Category findById(String id);
}