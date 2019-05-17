package dev.collegue.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOTES")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "TARGET_EMAIL")
	private String targetEmail;
	@Enumerated
	@Column(name = "VOTE_TYPE")
	private VoteType voteType;
	@Column(name = "DATE_TIME_VOTE")
	private LocalDateTime voteDateTime;

	public Vote() {
	}

	public Vote(String targetEmail, VoteType voteType) {
		this.targetEmail = targetEmail;
		this.voteType = voteType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetEmail() {
		return targetEmail;
	}

	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

	public LocalDateTime getVoteDateTime() {
		return voteDateTime;
	}

	public void setVoteDateTime(LocalDateTime dateVote) {
		this.voteDateTime = dateVote;
	}
	
	

}
