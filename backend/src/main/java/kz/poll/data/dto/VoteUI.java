package kz.poll.data.dto;

import java.util.Date;

public class VoteUI {
	String id;
	String user;
	Date createdDate;
	String pollId;
	String candidateId;

	public String getId() {
		return id;
	}
	
	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", user=" + user + ", createdDate="
				+ createdDate + "]";
	}
}
