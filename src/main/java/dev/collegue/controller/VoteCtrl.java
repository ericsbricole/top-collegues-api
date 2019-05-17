package dev.collegue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.exceptions.VoteException;
import dev.collegue.model.Participant;
import dev.collegue.model.Vote;
import dev.collegue.service.VoteService;

@RestController
@RequestMapping("/votes")
@CrossOrigin
public class VoteCtrl {

	@Autowired
	VoteService voteService;

	@PostMapping("/add")
	public ResponseEntity<Participant> addVote(@RequestBody Vote vote) throws VoteException {
		String voterEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		Participant participantToVoteFor = voteService.vote(vote, voterEmail);
		return ResponseEntity.ok().body(participantToVoteFor);
	}

	@GetMapping("/participants")
	public ResponseEntity<List<Participant>> allParticipants() {
		return ResponseEntity.ok(voteService.findAllVoters());
	}
	
	@ExceptionHandler(value = {VoteException.class})
	public ResponseEntity<String> handleVoteExceptions(VoteException e) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
	}

}
