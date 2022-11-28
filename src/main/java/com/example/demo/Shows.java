package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Shows {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int showsid;
	private String showname;
	private String showtime;
	
	@ManyToOne
	private Screen screen;
	
	@OneToMany
	private List<MovieQueue> moviequeue1;

	public int getShowsid() {
		return showsid;
	}

	public void setShowsid(int showsid) {
		this.showsid = showsid;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public String getShowtime() {
		return showtime;
	}

	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<MovieQueue> getMoviequeue1() {
		return moviequeue1;
	}

	public void setMoviequeue1(List<MovieQueue> moviequeue1) {
		this.moviequeue1 = moviequeue1;
	}
	
	

}
