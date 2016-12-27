package kz.poll.data.model;

import java.util.Date;
import java.util.List;

import kz.poll.data.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category {
	@Id
	private String id;

	private String name;
	private int pollCount;
	private List<String> polls;
	public int getPollCount() {
		return pollCount;
	}

	public void setPollCount(int pollCount) {
		this.pollCount = pollCount;
	}

	public List<String> getPolls() {
		return polls;
	}

	public void setPolls(List<String> polls) {
		this.polls = polls;
	}



	private Date createdDate;
	private User createdBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", questionCount="
				+ pollCount + ", questions=" + polls + "]";
	}
}
