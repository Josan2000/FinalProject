package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenid;
	private String screename;
	
	@ManyToOne
	private Multiplex multiplex;
	
	@OneToMany
	private List<Shows> shows;

	public int getScreenid() {
		return screenid;
	}

	public void setScreenid(int screenid) {
		this.screenid = screenid;
	}

	public String getScreename() {
		return screename;
	}

	public void setScreename(String screename) {
		this.screename = screename;
	}

	public Multiplex getMultiplex() {
		return multiplex;
	}

	public void setMultiplex(Multiplex multiplex) {
		this.multiplex = multiplex;
	}

	public List<Shows> getShows() {
		return shows;
	}

	public void setShows(List<Shows> shows) {
		this.shows = shows;
	}
	
	

}
