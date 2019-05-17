package dev.collegue.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.exceptions.VoteException;
import dev.collegue.model.Participant;
import dev.collegue.model.Vote;
import dev.collegue.model.VoteType;
import dev.collegue.repository.ParticipantRepository;
import dev.collegue.repository.VoteRepository;

@Service
public class VoteService {

	private final int LIKE_VALUE = 1;
	private final int DISLIKE_VALUE = -1;

	@Autowired
	ParticipantRepository participantRepository;
	@Autowired
	VoteRepository voteRepository;

	@Transactional
	public Participant vote(Vote vote, String voterEmail) throws VoteException {
		String targetEmail = vote.getTargetEmail();
		Participant voter = participantRepository.findById(voterEmail)
				.orElseThrow(() -> new UsernameNotFoundException("The voter was not found"));
		Participant target = participantRepository.findById(targetEmail)
				.orElseThrow(() -> new UsernameNotFoundException("The target participant of the vote was not found"));

		if (voter.equals(target)) {
			throw new VoteException("The voter is voting for himself/herself");
		}

		vote.setVoteDateTime(LocalDateTime.now());
		List<Vote> voterVotes = voter.getVotes();
		voterVotes.add(vote);
		voter.setVotes(voterVotes);
		voteRepository.save(vote);
		Integer newScore = calculateScore(target);
		target.setScore(newScore);

		return target;
	}

	public Integer calculateScore(Participant target) throws VoteException {
		List<Vote> votesForTarget = voteRepository.findByTargetEmail(target.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("No vote for " + target.getEmail() + " vas found"));
		Integer newScore = votesForTarget.stream()
				.mapToInt(vote -> vote.getVoteType().equals(VoteType.LIKE) ? LIKE_VALUE : DISLIKE_VALUE)
				.reduce((i1, i2) -> i1 + i2).orElseThrow(() -> new VoteException(
						"An error occured while calculating the score of " + target.getEmail()));
		return newScore;
	}

	@Transactional
	public void subscribeVoter(Participant participant) {
		participantRepository.save(participant);
	}

	public Participant findByEmail(String email) {
		return participantRepository.findById(email).orElseThrow(
				() -> new UsernameNotFoundException("No participant with the email " + email + " was found"));
	}

	public List<Participant> findAllVoters() {
		return participantRepository.findAll();
	}

}
