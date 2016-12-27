package kz.poll.data.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Poll {
	@Id
	private String id;
	private String subject;
	private String content;
	private String image;
	private String category;

	private List<Candidate> candidates;
	private Map<String, Comment> comments;
	private Map<String, Vote> votes;
	private List<String> tags;
	private int viewCount;
	private int voteCount;

	@DBRef
	private User createdBy;
	private Date createdDate;

	public Poll() {

	}

	public Poll(String subject, String content) {
		this.subject = subject;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id_) {
		id = id_;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject_) {
		subject = subject_;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content_) {
		content = content_;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate_) {
		createdDate = createdDate_;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy_) {
		createdBy = createdBy_;
	}

	public Map<String, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<String, Comment> comments) {
		this.comments = comments;
	}

	public Map<String, Vote> getVotes() {
		return votes;
	}

	public void setVotes(Map<String, Vote> votes) {
		this.votes = votes;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", subject=" + subject + ", content="
				+ content + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", comments=" + comments
				+ ", votes=" + votes + ", tags="
				+ tags + ", viewCount=" + viewCount + "]";
	}

	

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
