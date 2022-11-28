package com.example.demo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Multiplex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int multiplexid;
	private String multiplexname;
	private String location;
	
	@OneToMany
	private List<Screen> screen;

	public int getMultiplexid() {
		return multiplexid;
	}

	public void setMultiplexid(int multiplexid) {
		this.multiplexid = multiplexid;
	}

	public String getMultiplexname() {
		return multiplexname;
	}

	public void setMultiplexname(String multiplexname) {
		this.multiplexname = multiplexname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Screen> getScreen() {
		return screen;
	}

	public void setScreen(List<Screen> screen) {
		this.screen = screen;
	}
	
	

}
