package dev.collegue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CollegueToVote {

	@Id
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRENOMS")
	private String prenoms;
	@Column(name = "PHOTOURL")
	private String photoUrl;
	@Column(name = "PASSWORD")
	private String password;
	
	public CollegueToVote(String email, String nom, String prenoms, String photoUrl, String password) {
		this.email = email;
		this.nom = nom;
		this.prenoms = prenoms;
		this.photoUrl = photoUrl;
		this.password = password;
	}

}
