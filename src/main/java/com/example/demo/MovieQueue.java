package com.example.demo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class MovieQueue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int queueid;
	private int availableseats;
	private Date date;
	
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private Shows shows;

	public int getQueueid() {
		return queueid;
	}

	public void setQueueid(int queueid) {
		this.queueid = queueid;
	}

	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Shows getShows() {
		return shows;
	}

	public void setShows(Shows shows) {
		this.shows = shows;
	}
	
	

}
