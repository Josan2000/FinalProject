package com.example.demo;

import java.sql.Date;

import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


public class MovieSample {
	
	
	private int availableseats;
	private String fromdate;
	private String todate;
	
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private Shows shows;

	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
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
