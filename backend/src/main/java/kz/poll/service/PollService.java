package kz.poll.service;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import kz.poll.common.util.mapper.CandidateMapper;
import kz.poll.common.util.mapper.CommentMapper;
import kz.poll.common.util.mapper.PollMapper;
import kz.poll.data.model.*;
import kz.poll.data.repo.CategoryRepository;
import kz.poll.data.repo.PollRepository;
import kz.poll.data.repo.UserRepository;
import kz.poll.data.dto.CandidateUI;
import kz.poll.data.dto.CommentUI;
import kz.poll.data.dto.PollUI;
import kz.poll.data.dto.VoteUI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PollService {

	@Autowired
	private PollRepository qaRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	MongoTemplate template;
	@Autowired 
	GridFsTemplate gtemplate;

	private PollMapper qMapper = new PollMapper();
	private CandidateMapper aMapper=new CandidateMapper();
	private CommentMapper cMapper = new CommentMapper();
	private Logger logger = Logger.getLogger(UserService.class);

	public PollUI create(PollUI uiBean, Principal principal, MultipartFile file) throws IOException {
		Poll newQ = qMapper.toPersistenceBean(uiBean);
		User user = userRepo.findByUserName("abzal");
		if (user != null) {
			newQ.setCreatedBy(user);
		}
		if(!file.isEmpty()){
			String filename=Long.toHexString(Double.doubleToLongBits(Math.random()))+".jpg";
			gtemplate.store(file.getInputStream(), filename);
			newQ.setImage(filename);
		}
		newQ.setCreatedDate(Calendar.getInstance().getTime());
		newQ = qaRepo.save(newQ);
		addCategory(newQ);//add category

		logger.debug(newQ);
		return qMapper.toUIBean(newQ);
	}

	public List<PollUI> findAll() {
		return qMapper.toUIBean(qaRepo.findAll());
	}

	public Page<PollUI> findAll(Pageable pageable) {
		return qMapper.toUIBean(qaRepo.findAll(pageable), pageable);
	}

	public PollUI findBySubject(String subject) {
		return qMapper.toUIBean(qaRepo.findBySubject(subject));
	}

	public PollUI findById(String id) {
		return qMapper.toUIBean(qaRepo.findById(id));
	}

	public List<PollUI> findById(List<String> id) {
		List<Poll> questions = new ArrayList<Poll>();
		questions = template.find(new Query(Criteria.where("id").in(id)),
				Poll.class);

		return qMapper.toUIBean(questions);
	}

	public PollUI findById(String id, Principal principal) {
		template.updateFirst(new Query(Criteria.where("id").is(id)),
				new Update().inc("viewCount", 1), Poll.class);

		return qMapper.toUIBean(qaRepo.findById(id));
	}

	public PollUI update(PollUI question) {
		Poll existing = qaRepo.findBySubject(question.getSubject());

		return qMapper.toUIBean(qaRepo.save(existing));
	}

	public Boolean delete(PollUI question) {
		Poll existing = qaRepo.findBySubject(question.getSubject());

		if (existing == null) {
			return false;
		}

		qaRepo.delete(existing);
		return true;
	}

	public PollUI addCandidate(CandidateUI candidate, Principal principal) {
		PollUI result = new PollUI();

		Candidate newCandidate = aMapper.toPersistenceBean(candidate);
		newCandidate.setId(UUID.randomUUID().toString());
		newCandidate.setCreatedDate(Calendar.getInstance().getTime());
		User user = userRepo.findByUserName(principal.getName());
		
		/*Query q = new Query(Criteria.where("id").is(candidate.getPollId()));
		Update updateQ = new Update().set("answers." + newAnswer.getId(),
				newAnswer);
		template.updateFirst(q, updateQ, Poll.class);

		result = qMapper.toUIBean(template.findOne(q, Poll.class));*/
		return result;
	}

	public PollUI addPollComment(CommentUI comment, Principal principal) {
		PollUI result = new PollUI();
		Comment newComment = cMapper.toPersistenceBean(comment);
		newComment.setId(UUID.randomUUID().toString());
		newComment.setCreatedDate(Calendar.getInstance().getTime());
		User user = userRepo.findByUserName(principal.getName());
		if (user != null) {
			newComment.setCreatedBy(user);
		}

		Query q = new Query(Criteria.where("id").is(comment.getPollId()));
		Update updateQ = new Update().set("comments." + newComment.getId(),
				newComment);
		template.updateFirst(q, updateQ, Poll.class);

		result = qMapper.toUIBean(template.findOne(q, Poll.class));
		return result;
	}

	public PollUI addAnswerComment(CommentUI comment, Principal principal) {
		PollUI result = new PollUI();
		Comment newComment = cMapper.toPersistenceBean(comment);
		newComment.setId(UUID.randomUUID().toString());
		newComment.setCreatedDate(Calendar.getInstance().getTime());
		User user = userRepo.findByUserName(principal.getName());
		if (user != null) {
			newComment.setCreatedBy(user);
		}

		Query q = new Query(Criteria.where("id").is(comment.getPollId()));
		Update updateQ = new Update().set("answers." + comment.getAnswerId()
				+ ".comments." + newComment.getId(), newComment);
		template.updateFirst(q, updateQ, Poll.class);

		result = qMapper.toUIBean(template.findOne(q, Poll.class));
		return result;
	}

	public PollUI addVote(String questionId, Principal principal) {
		PollUI result = new PollUI();
		User user = userRepo.findByUserName(principal.getName());

		Query q = new Query(Criteria.where("id").is(questionId));
		if (user != null) {
			Vote vote = new Vote();
			vote.setId(user.getId());
			vote.setUser(user.getUserName());
			vote.setCreatedDate(Calendar.getInstance().getTime());

			Update updateQ = new Update().set("votes." + user.getId(), vote);
			template.updateFirst(q, updateQ, Poll.class);
		}

		result = qMapper.toUIBean(template.findOne(q, Poll.class));
		return result;
	}
	
	public PollUI addVoteForCandidate(VoteUI uiBean, Principal user){
		return null;
	}
	
	public InputStream getImageForPoll(String filename) throws IllegalStateException, IOException{
		return gtemplate.getResource(filename).getInputStream();
	}

	/*private void addTags(Poll question) {
		for (String tagName : question.getTags()) {
			Tag existingTag = tagRepo.findByName(tagName);
			if (existingTag != null) {
				Query q = new Query(Criteria.where("id")
						.is(existingTag.getId()));
				template.updateFirst(
						q,
						new Update().push("questions", question.getId()).inc(
								"questionCount", 1), Tag.class);
			} else {
				Tag newTag = new Tag();
				newTag.setName(tagName);
				newTag.setCreatedBy(question.getCreatedBy());
				newTag.setCreatedDate(Calendar.getInstance().getTime());
				//newTag.setPollCount(1);

				List<String> questions = new ArrayList<String>();
				questions.add(question.getId());
				newTag.setPolls(questions);

				template.insert(newTag);
			}
		}
	}
*/	
	
	private void addCategory(Poll poll){
		Category existingCategory = categoryRepo.findByName(poll.getCategory());
		if (existingCategory != null) {
			Query q = new Query(Criteria.where("id")
					.is(existingCategory.getId()));
			template.updateFirst(
					q,
					new Update().push("polls", poll.getId()).inc(
							"pollCount", 1), Category.class);
		} else {
			Category newTag = new Category();
			newTag.setName(poll.getCategory());
			newTag.setCreatedBy(poll.getCreatedBy());
			newTag.setCreatedDate(Calendar.getInstance().getTime());
			//newTag.setPollCount(1);

			List<String> polls = new ArrayList<String>();
			polls.add(poll.getId());
			newTag.setPolls(polls);

			template.insert(newTag);
		}
	}
	
	
	
}
