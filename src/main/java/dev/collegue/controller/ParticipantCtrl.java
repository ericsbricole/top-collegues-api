package dev.collegue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.model.Participant;
import dev.collegue.service.ParticipantService;

@RestController
@RequestMapping("/participants")
@CrossOrigin
public class ParticipantCtrl {

	@Autowired
	private ParticipantService participantService;
	
	@GetMapping
	public ResponseEntity<List<Participant>> findAllParticipants() {
		return ResponseEntity.ok(participantService.findAllVoters());
	}
	
	@GetMapping("/{matricule}")
	public ResponseEntity<Participant> findParticipantByMatricule(@PathVariable String matricule) {
		return ResponseEntity.ok(participantService.findByMatricule(matricule));
	}
	
}
