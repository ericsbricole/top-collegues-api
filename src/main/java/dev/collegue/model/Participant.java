package dev.collegue.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Participant {

	@Id
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHOTOURL")
	private String photoUrl;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRENOMS")
	private String prenoms;
	@Column(name = "SCORE")
	private Integer score;
	@OneToMany
	@Column(name = "VOTES")
	private List<Vote> votes;

	public Participant() {
		score = 0;
	}

	public Participant(String email, String photoUrl, String nom, String prenoms, Integer score) {
		this.email = email;
		this.photoUrl = photoUrl;
		this.nom = nom;
		this.prenoms = prenoms;
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}