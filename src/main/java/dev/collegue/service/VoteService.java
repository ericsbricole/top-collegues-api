package dev.collegue.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.model.Voter;
import dev.collegue.repository.VoteRepository;

@Service
public class VoteService {

	private final short VALUE_PER_VOTE = 100;

	@Autowired
	VoteRepository voteRepository;

	@Transactional
	public Voter vote(String email) {
		Voter collegueToVote = voteRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException(
				"There was not a collegue " + email + " in the database to vote for"));
		collegueToVote.setScore(collegueToVote.getScore() + VALUE_PER_VOTE);
		return collegueToVote;
	}

	@Transactional
	public void subscribeVoter(Voter voteEntity) {
		voteRepository.save(voteEntity);
	}

	public Voter findByEmail(String email) {
		return voteRepository.findById(email).orElseThrow(
				() -> new UsernameNotFoundException("There was not a collegue " + email + " in the database"));
	}

	public List<Voter> findAllVoters() {
		return voteRepository.findAll();
	}

}
