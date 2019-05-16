package dev.collegue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.model.Participant;
import dev.collegue.model.Vote;
import dev.collegue.service.VoteService;

@RestController
@CrossOrigin
public class VoteCtrl {
	
	@Autowired
	VoteService voteService;

	@PostMapping("/vote")
	public ResponseEntity<Vote> addPoints(@RequestBody Vote vote) {
		String voterEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		Participant participantToVoteFor = voteService.vote(vote, voterEmail);
		return ResponseEntity.ok().body(vote);
	}

}
