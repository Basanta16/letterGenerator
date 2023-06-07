package com.basanta.letter.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "letter_details")
public class letterDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int letter_id;
	
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 25)
	private String receipient_name;
	
	private String from_date;
	
	private String to_date;
	
	private String team_name;
	
	private String email;
	
	private String phone;
	
	@Column(length = 25)
	private String cause;
	
	@Column(length = 5)
	private int duration;
	

	public int getLetter_id() {
		return letter_id;
	}

	public void setLetter_id(int letter_id) {
		this.letter_id = letter_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReceipient_name() {
		return receipient_name;
	}

	public void setReceipient_name(String receipient_name) {
		this.receipient_name = receipient_name;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	
}
