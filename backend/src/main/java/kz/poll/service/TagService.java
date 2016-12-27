package kz.poll.service;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import kz.poll.common.util.mapper.QuestionMapper;
import kz.poll.common.util.mapper.TagMapper;
import kz.poll.data.model.Tag;
import kz.poll.data.model.User;
import kz.poll.data.repo.TagRepository;
import kz.poll.data.repo.UserRepository;
import kz.poll.data.dto.TagUI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TagRepository tagRepo;
	@Autowired
	MongoTemplate template;

	TagMapper tMapper = new TagMapper();
	QuestionMapper qMapper = new QuestionMapper();
	private Logger logger = Logger.getLogger(TagService.class);

	public TagUI create(TagUI uiBean, Principal principal) {
		Tag newTag = tMapper.toPersistenceBean(uiBean);

		User user = userRepo.findByUserName(principal.getName());
		if (user != null) {
			newTag.setCreatedBy(user);
		}
		newTag.setCreatedDate(Calendar.getInstance().getTime());

		newTag = tagRepo.save(newTag);

		logger.debug(newTag);
		return tMapper.toUIBean(newTag);
	}

	public List<TagUI> findAll() {
		return tMapper.toUIBean(tagRepo.findAll());
	}

	public Page<TagUI> findAll(Pageable pageable) {
		return tMapper.toUIBean(tagRepo.findAll(pageable), pageable);
	}

	public TagUI findByName(String name) {
		return tMapper.toUIBean(tagRepo.findByName(name));
	}

	public TagUI findById(String id) {
		return tMapper.toUIBean(tagRepo.findById(id));
	}

	public Tag getQuestionsSliceByTagName(String tagName, int currentPage,
			int pageSize) {
		Query q = new Query(Criteria.where("name").is(tagName));
		q.fields().slice("questions", (currentPage - 1) * pageSize, pageSize);
		Tag tag = template.findOne(q, Tag.class);

		return tag;
	}

	public Tag getQuestionsSliceByTagId(String tagId, int currentPage,
			int pageSize) {
		Query q = new Query(Criteria.where("id").is(tagId));
		q.fields().slice("questions", (currentPage - 1) * pageSize, pageSize);
		Tag tag = template.findOne(q, Tag.class);

		return tag;
	}
}
