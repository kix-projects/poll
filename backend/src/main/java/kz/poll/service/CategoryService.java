package kz.poll.service;

import kz.poll.data.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	MongoTemplate template;
	
	public Category getPollsSliceByTagName(String tagName, int currentPage,
										   int pageSize) {
		Query q = new Query(Criteria.where("name").is(tagName));
		q.fields().slice("polls", (currentPage - 1) * pageSize, pageSize);
		Category category = template.findOne(q, Category.class);

		return category;
	}

}
