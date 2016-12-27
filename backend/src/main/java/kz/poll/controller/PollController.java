package kz.poll.controller;

import java.io.IOException;
import java.security.Principal;

import kz.poll.common.util.ui.PaginationInfo;
import kz.poll.service.PollService;
import kz.poll.data.dto.CandidateUI;
import kz.poll.data.dto.CommentUI;
import kz.poll.data.dto.PollUI;
import kz.poll.data.dto.VoteUI;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/poll")
public class PollController {
	@Autowired
	PollService pollService;

	private Logger logger = Logger.getLogger(PollController.class);

	@RequestMapping
	public String getStartPage() {
		return "poll/list";
	}
	
	@RequestMapping(value = "/list")
	public String list(@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model) {

		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		//String listAction = "poll/list";
		String listAction = "opros/index";
		Pageable pageable = new PageRequest(currentPage - 1, pageLimit);
		Page<PollUI> polls = pollService.findAll(pageable);
		PaginationInfo pageInfo = new PaginationInfo(currentPage,
				polls.getTotalElements(), pageLimit, listAction);

		model.addAttribute("polls", polls.getContent());
		model.addAttribute("pageInfo", pageInfo);

		logger.debug("Pageable :: total: " + polls.getTotalElements()
				+ " polls: " + polls.getContent());

		return listAction;
	}

	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable String id, Model model, Principal principal)
			throws Exception {

		PollUI poll = pollService.findById(id, principal);
		model.addAttribute("poll", poll);

		logger.debug(poll);
		return "poll/view";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() throws Exception {
		return "opros/newpoll";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(PollUI data, MultipartFile imageData, Model model, Principal principal) throws IOException {
		String resultPage = "opros/newpoll";		
		if (pollService.findBySubject(data.getSubject()) == null) {
			PollUI savedPoll = pollService.create(data, principal,imageData);			
			if (savedPoll != null) {
				logger.debug("Poll Created Succesfully");
				resultPage = "redirect:list";
			} else {
				logger.debug("Error trying to create account.");
				resultPage = "newpoll";
			}
		} else {
			logger.debug("Poll already exists");
		}

		return resultPage;
	}

	@RequestMapping(value = "/answer/add", method = RequestMethod.POST)
	public String addAnswer(CandidateUI data, Model model, Principal principal) {
		String resultPage = "poll/view";

		PollUI poll = pollService.addCandidate(data, principal);
		model.addAttribute("poll", poll);

		return resultPage;
	}

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public String addPollComment(CommentUI data, Model model,
								 Principal principal) {
		String resultPage = "poll/view";

		PollUI poll = pollService.addPollComment(data, principal);
		model.addAttribute("poll", poll);

		return resultPage;
	}

	@RequestMapping(value = "/candidate/vote", method = RequestMethod.POST)
	public String addVoteForCandidate(CandidateUI data, Model model,Principal principal) {
		String resultPage = "poll/view";
		logger.debug(data.getId());
		PollUI poll = pollService.addVoteForCandidate(new VoteUI(), principal);
		model.addAttribute("poll", poll);
		return resultPage;
	}

	@RequestMapping(value = "/vote")
	public String addVote(Model model, String pollId, Principal principal) {
		String resultPage = "poll/view";
		PollUI poll = pollService.addVote(pollId, principal);
		model.addAttribute("poll", poll);
		return resultPage;
	}
	
	@RequestMapping("/comment")
	public String getCommentPage() {
		return "opros/comment";
	}
	
	@RequestMapping("/enter")
	public String getEnterPage() {
		return "opros/enter";	}
	
	@RequestMapping("/image")
	public ResponseEntity<byte[]> getphoto(@RequestParam(required = true) String filename) throws IOException {		 
		final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);
	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(pollService.getImageForPoll(filename)), headers, HttpStatus.CREATED);
	}
		
	
}
