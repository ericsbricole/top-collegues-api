package dev.collegue.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.collegue.model.Collegue;
import dev.collegue.model.CollegueToVote;
import dev.collegue.model.InfosAuthent;

@RestController
public class AuthCtrl {

	@Autowired
	private RestTemplate rt;

	@Value("${collegue-api.port}")
	private String COLLEGUE_API_PORT;

	@Value("${collegue-api.url}")
	private String COLLEGUE_API_URL;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@PostMapping("/auth")
	public ResponseEntity<Collegue> authenticate(@RequestBody InfosAuthent infosAuthent, HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException, URISyntaxException {
		String pathToReAuth = COLLEGUE_API_URL + "/auth";
		String pathToGetCollegueCourant = COLLEGUE_API_URL + "/me";

		ResponseEntity<?> responseEntity = rt.postForEntity(pathToReAuth, infosAuthent, InfosAuthent.class);
		String jetonJWT = responseEntity.getHeaders().getFirst("Set-Cookie").split(";")[0].split("=")[1];

		Cookie authCookie = new Cookie(TOKEN_COOKIE, jetonJWT);

		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		resp.addCookie(authCookie);

		RequestEntity<?> rresponseEntityFromCollegueApi = RequestEntity.get(new URI(COLLEGUE_API_URL + "/me")).header("Cookie", authCookie.getValue()).build();
		Collegue c = rt.postForEntity(pathToGetCollegueCourant, infosAuthent, Collegue.class).getBody();
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}

}
