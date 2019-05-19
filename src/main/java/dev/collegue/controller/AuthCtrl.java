package dev.collegue.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.collegue.model.Participant;
import dev.collegue.service.ParticipantService;
import dev.collegue.service.VoteService;
import dev.collegue.model.InfosAuthent;

@RestController
@CrossOrigin
public class AuthCtrl {

	@Autowired
	private RestTemplate rt;
	@Autowired
	private ParticipantService participantService;
	

	@Value("${collegue-api.url}")
	private String COLLEGUE_API_URL;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Transactional
	@PostMapping("/auth")
	public ResponseEntity<Participant> authenticate(@RequestBody InfosAuthent infosAuthent, HttpServletResponse resp)
			throws IOException, ServletException, URISyntaxException {
		String pathToReAuth = COLLEGUE_API_URL + "/auth";

		ResponseEntity<?> responseEntity = rt.postForEntity(pathToReAuth, infosAuthent, InfosAuthent.class);
		String jetonJWT = responseEntity.getHeaders().getFirst("Set-Cookie").split(";")[0].split("=")[1];

		Cookie authCookie = new Cookie(TOKEN_COOKIE, jetonJWT);

		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		resp.addCookie(authCookie);

		RequestEntity<?> requestEntityForCollegueApi = RequestEntity.get(new URI(COLLEGUE_API_URL + "/me"))
				.header("Cookie", responseEntity.getHeaders().getFirst("Set-Cookie")).build();
		ResponseEntity<Participant> connectedParticipantResponseEntity = rt.exchange(requestEntityForCollegueApi,
				Participant.class);
		Participant participant = connectedParticipantResponseEntity.getBody();
		participantService.subscribeVoter(participant);
		return ResponseEntity.ok(participant);
	}

	@GetMapping("/me")
	public ResponseEntity<Participant> me() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Participant voteEntity = participantService.findByEmail(email);
		return ResponseEntity.ok(voteEntity);
	}

}
