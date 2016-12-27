package kz.poll.data.dto;

import java.util.Date;
import java.util.List;



public class PollUI {
	private String id;
	private String subject;
	private String content;
	private String category;
	private String image;
	private List<CandidateUI> candidates;
	private List<String> options;	
	private List<CommentUI> comments;
	
	
	private int viewCount;
	private int voteCount;
	private boolean alreadyVoted=false;
	
	private UserUI createdBy;
	private Date createdDate;
	
	public List<CandidateUI> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<CandidateUI> candidates) {
		this.candidates = candidates;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public List<CommentUI> getComments() {
		return comments;
	}
	public void setComments(List<CommentUI> comments) {
		this.comments = comments;
	}
	
	public UserUI getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserUI createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public boolean isAlreadyVoted() {
		return alreadyVoted;
	}
	public void setAlreadyVoted(boolean alreadyVoted) {
		this.alreadyVoted = alreadyVoted;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
