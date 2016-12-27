package kz.poll.common.util.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import kz.poll.data.model.Candidate;
import kz.poll.data.model.Poll;
import kz.poll.data.dto.PollUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PollMapper {
	CandidateMapper candidateMapper = new CandidateMapper();
	CommentMapper commentmapper = new CommentMapper();
	UserMapper userMapper = new UserMapper();
	VoteMapper voteMapper = new VoteMapper();
	TagMapper tagMapper = new TagMapper();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public PollUI toUIBean(Poll pBean) {
		PollUI uiBean = null;

		if (pBean != null) {
			uiBean = new PollUI();

			if (pBean.getCandidates() != null && pBean.getCandidates().size() > 0) {
				uiBean.setCandidates(candidateMapper.toUIBean(pBean.getCandidates()));
			}

			if (pBean.getComments() != null && pBean.getComments().size() > 0) {
				uiBean.setComments(commentmapper.toUIBean(pBean.getComments()));
			}

			if (pBean.getCreatedBy() != null) {
				uiBean.setCreatedBy(userMapper.toUIBean(pBean.getCreatedBy()));
			}			
			
			uiBean.setCategory(pBean.getCategory());
			uiBean.setContent(pBean.getContent());
			uiBean.setCreatedDate(pBean.getCreatedDate());
			uiBean.setId(pBean.getId());
			uiBean.setSubject(pBean.getSubject());
			uiBean.setViewCount(pBean.getViewCount());
			uiBean.setVoteCount(pBean.getVoteCount());
			if(pBean.getImage()!=null)
				uiBean.setImage("/poll/image?filename="+pBean.getImage());
		}

		return uiBean;
	}

	public List<PollUI> toUIBean(List<Poll> polls) {
		List<PollUI> uiBeans = new ArrayList<PollUI>();

		for (Poll poll : polls) {
			uiBeans.add(toUIBean(poll));
		}
		return uiBeans;
	}

	public Page<PollUI> toUIBean(Page<Poll> polls, Pageable pageable) {
		Page<PollUI> uiBeans = new PageImpl<PollUI>(
				toUIBean(polls.getContent()), pageable,
				polls.getTotalElements());

		return uiBeans;
	}

	public Poll toPersistenceBean(PollUI uiBean) {
		Poll poll = null;

		if (uiBean != null) {
			poll = new Poll();

			if (uiBean.getComments() != null && uiBean.getComments().size() > 0) {
				poll.setComments(commentmapper.toPersistenceBeanMap(uiBean
						.getComments()));
			}

			if (uiBean.getCreatedBy() != null) {
				poll.setCreatedBy(userMapper.toPersistenceBean(uiBean
						.getCreatedBy()));
			}			
			List<Candidate> cns=new ArrayList<Candidate>();
			for (String option : uiBean.getOptions()) {
				Candidate candidate=new Candidate();
				candidate.setId(UUID.randomUUID().toString());
				candidate.setContent(option);
				cns.add(candidate);				
			}
			poll.setCategory(uiBean.getCategory());
			poll.setCandidates(cns);			
			poll.setContent(uiBean.getContent());
			poll.setCreatedDate(uiBean.getCreatedDate());
			poll.setId(uiBean.getId());
			poll.setSubject(uiBean.getSubject());
			poll.setViewCount(uiBean.getViewCount());
			poll.setVoteCount(uiBean.getVoteCount());
		}
		return poll;
	}

	public List<Poll> toPersistenceBean(List<PollUI> uiPolls) {
		List<Poll> polls = new ArrayList<Poll>();

		for (PollUI uiBean : uiPolls) {
			polls.add(toPersistenceBean(uiBean));
		}
		return polls;
	}
}
