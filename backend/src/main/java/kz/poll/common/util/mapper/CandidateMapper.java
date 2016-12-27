package kz.poll.common.util.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.poll.data.model.Candidate;
import kz.poll.data.dto.CandidateUI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CandidateMapper {
	VoteMapper commentmapper = new VoteMapper();
	
	public CandidateUI toUIBean(Candidate pBean) {
		CandidateUI uiBean = null;
		if (pBean != null) {
			uiBean = new CandidateUI();
			uiBean.setContent(pBean.getContent());
			uiBean.setVoteCount(pBean.getVoteCount());
			uiBean.setId(pBean.getId());			
		}
		return uiBean;
	}

	public List<CandidateUI> toUIBean(List<Candidate> candidates) {
		List<CandidateUI> uiBeans = new ArrayList<CandidateUI>();

		for (Candidate candidate : candidates) {
			uiBeans.add(toUIBean(candidate));
		}
		return uiBeans;
	}

	public List<CandidateUI> toUIBean(Map<String, Candidate> candidates) {
		List<CandidateUI> uiBeans = new ArrayList<CandidateUI>();

		for (String key : candidates.keySet()) {
			CandidateUI candidate = toUIBean(candidates.get(key));
			candidate.setId(key);
			uiBeans.add(candidate);
		}
		return uiBeans;
	}

	public Page<CandidateUI> toUIBean(Page<Candidate> candidates, Pageable pageable) {
		Page<CandidateUI> uiBeans = new PageImpl<CandidateUI>(
				toUIBean(candidates.getContent()), pageable,
				candidates.getTotalElements());

		return uiBeans;
	}

	public Candidate toPersistenceBean(CandidateUI uiBean) {
		Candidate candidate = null;

		if (uiBean != null) {
			candidate = new Candidate();
			candidate.setContent(uiBean.getContent());
			candidate.setId(uiBean.getId());
		}
		return candidate;
	}

	public List<Candidate> toPersistenceBean(List<CandidateUI> uiCandidates) {
		List<Candidate> candidates = new ArrayList<Candidate>();

		for (CandidateUI uiBean : uiCandidates) {
			candidates.add(toPersistenceBean(uiBean));
		}
		return candidates;
	}

	public Map<String, Candidate> toPersistenceBeanMap(List<CandidateUI> uiCandidates) {
		Map<String, Candidate> candidates = new HashMap<String, Candidate>();

		for (CandidateUI uiBean : uiCandidates) {
			candidates.put(uiBean.getId(), toPersistenceBean(uiBean));
		}
		return candidates;
	}
}
