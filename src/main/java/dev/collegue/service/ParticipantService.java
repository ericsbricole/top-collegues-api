package dev.collegue.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.model.Participant;
import dev.collegue.repository.ParticipantRepository;

@Service
public class ParticipantService {
	
	@Autowired
	ParticipantRepository participantRepository;

	@Transactional
	public void subscribeVoter(Participant participant) {
		participantRepository.save(participant);
	}

	public Participant findByMatricule(String matricule) {
		return participantRepository.findById(matricule).orElseThrow(
				() -> new UsernameNotFoundException("No participant with the email " + matricule + " was found"));
	}
	
	public Participant findByEmail(String email) {
		return participantRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("No participant with the email " + email + " was found"));
	}

	public List<Participant> findAllVoters() {
		return participantRepository.findAll();
	}
	
}
