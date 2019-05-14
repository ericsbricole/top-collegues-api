package dev.collegue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.model.Voter;
import dev.collegue.service.VoteService;

@RestController
@CrossOrigin
public class VoteCtrl {
	
	@Autowired
	VoteService voteService;

	@PostMapping("/vote")
	public ResponseEntity<Voter> addPoints(@RequestBody Voter voteEntity) {
		Voter collegueToVoteFor = voteService.vote(voteEntity.getTargetEmail());
		return ResponseEntity.ok().body(collegueToVoteFor);
	}

}
