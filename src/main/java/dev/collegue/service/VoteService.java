package dev.collegue.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.model.Participant;
import dev.collegue.model.Vote;
import dev.collegue.repository.ParticipantRepository;
import dev.collegue.repository.VoteRepository;

@Service
public class VoteService {

	private final short VALUE_PER_VOTE = 100;

	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	VoteRepository voteRepository;

	@Transactional
	public Participant vote(Vote vote, String voterEmail) {
		String targetEmail = vote.getTargetEmail();
		participantRepository.findById(voterEmail)
				.orElseThrow(() -> new UsernameNotFoundException("the voter was not found"));
		return null;
	}

	@Transactional
	public void subscribeVoter(Participant voteEntity) {
		participantRepository.save(voteEntity);
	}

	public Participant findByEmail(String email) {
		return participantRepository.findById(email).orElseThrow(
				() -> new UsernameNotFoundException("There was not a collegue " + email + " in the database"));
	}

	public List<Participant> findAllVoters() {
		return participantRepository.findAll();
	}

}
