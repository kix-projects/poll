package kz.poll.data.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Candidate {
	@Id
	private String id;

	private String content;
	
	private int voteCount;
	
	private List<Vote> votes;

	private Date createdDate;
	private Date editedDate;

	public Candidate() {

	}

	public Candidate(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id_) {
		id = id_;
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

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate_) {
		editedDate = editedDate_;
	}

	
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", content=" + content + ", createdDate="
				+ createdDate + ", editedDate="
				+ editedDate + "]";
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
}
