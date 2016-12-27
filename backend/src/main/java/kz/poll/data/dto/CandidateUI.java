package kz.poll.data.dto;

public class CandidateUI {
	
	private String id;
	
	private String pollId;

	private String content;
	
	private int voteCount;
			
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
		
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", content=" + content + ", createdDate="
				 + "]";
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
}
