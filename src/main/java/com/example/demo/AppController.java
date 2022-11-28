package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {
	
	@Autowired
	MovieRepository mRepo;
	@Autowired
	MultiplexRepository muRepo;
	@Autowired
	ScreenRepository sRepo;
	@Autowired
	ShowsRepository ssRepo;
	@Autowired
	MovieQueueRepository mqRepo;
	@Autowired
	BookingRepository bookRepo;
	
	@Autowired
	UserRepository uRepo;
	
	@Autowired
	LoginRepository loginRepo;

	
	@PostMapping("registermovie")
	public ResponseEntity movie(@RequestBody Movie movie) {

		movie.setTitle(movie.getTitle());
		movie.setGenre(movie.getGenre());
		movie.setPrice(movie.getPrice());

		// movie.setTheatre(movie.getTheatre());
		mRepo.save(movie);
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@PostMapping("registermultiplex")
	public ResponseEntity multiplex(@RequestBody Multiplex multiplex) {
		multiplex.setMultiplexname(multiplex.getMultiplexname());
		multiplex.setLocation(multiplex.getLocation());

		muRepo.save(multiplex);
		return new ResponseEntity<Multiplex>(multiplex, HttpStatus.OK);
	}

	@PostMapping("registerscreen")
	public ResponseEntity screen(@RequestBody Screen screen) {
		
		screen.setScreename(screen.getScreename());
		screen.setMultiplex(screen.getMultiplex());
		sRepo.save(screen);
		return new ResponseEntity<Screen>(screen, HttpStatus.OK);

	}
	@PostMapping("registershow")
	public ResponseEntity shows(@RequestBody Shows shows) {
		shows.setShowname(shows.getShowname());
		shows.setScreen(shows.getScreen());
		shows.setShowtime(shows.getShowtime());
		ssRepo.save(shows);
		return new ResponseEntity<Shows>(shows,HttpStatus.OK);
		
	}
	
	@PostMapping("moviequeue")
	public ResponseEntity MovieQueue(@RequestBody MovieSample moviesample){
		List<MovieQueue> queues = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(moviesample.getFromdate()); 
		LocalDate endDate = LocalDate.parse(moviesample.getTodate()); 
		while (!startDate.equals(endDate)) {
			java.sql.Date sqlDate = java.sql.Date.valueOf( startDate );
			MovieQueue queue = new MovieQueue();
			queue.setAvailableseats(moviesample.getAvailableseats());
			queue.setDate(sqlDate);
			queue.setMovie(moviesample.getMovie());
			queue.setShows(moviesample.getShows());
			queues.add(queue);
		    startDate = startDate.plusDays(1);
		    System.out.println(startDate);
		}
		mqRepo.saveAll(queues);
		
		return new ResponseEntity<List<MovieQueue>>(queues,HttpStatus.OK);
	}
	
 @GetMapping("bookticket/{showid}/{userid}/{seatcount}/{ticketdate}")
 public ResponseEntity Booking(@PathVariable int seatcount,@PathVariable int showid, @PathVariable int userid, @PathVariable String ticketdate) {
	LocalDate date = LocalDate.parse(ticketdate); 
	java.sql.Date sqlDate = java.sql.Date.valueOf( date );
	MovieQueue q=mqRepo.findByShow(showid, sqlDate);
	User u= uRepo.findById(userid).get();
	 Booking booking = new Booking();
	 booking.setSeat_count(seatcount);
	 booking.setUser(u);
	 
	 q.setAvailableseats(q.getAvailableseats()-booking.getSeat_count());
	 mqRepo.save(q);
	 
	 bookRepo.save(booking);
	 return new ResponseEntity<Booking>(booking,HttpStatus.OK);	 
 }
 
 @GetMapping("showmovie")
 public ResponseEntity<List<Movie>> getMovie(){
	 
	 return new ResponseEntity<List<Movie>>(mRepo.findAll(),HttpStatus.OK);
	 
 }
 
 @GetMapping("theatre/{movieid}")
 public ResponseEntity<List<Multiplex>> getTheatre(@PathVariable int movieid){
	 
//	mqRepo.findByMovie(movieid);
	HashSet<Multiplex> multiplex = new HashSet<Multiplex>();
	List<MovieQueue> movieQueue = mqRepo.findByMovie(movieid);
	movieQueue.forEach(queue -> {
		multiplex.add(queue.getShows().getScreen().getMultiplex());
	});
	 return new ResponseEntity(multiplex ,HttpStatus.OK);
	 
 }
 
 @GetMapping("screens/{movieid}/{theatreid}")
 public ResponseEntity<List<Screen>> getScreens(@PathVariable int movieid, @PathVariable int theatreid){
	 
//	mqRepo.findByMovie(movieid);
	HashSet<Screen> screen = new HashSet<Screen>();
	List<MovieQueue> movieQueue = mqRepo.findByMovie(movieid);
	movieQueue.forEach(queue -> {
		System.out.println(queue.getShows().getScreen().getMultiplex().getMultiplexid());
		if(theatreid == (int) queue.getShows().getScreen().getMultiplex().getMultiplexid()) {
		screen.add(queue.getShows().getScreen());
		}
	});
	 return new ResponseEntity(screen ,HttpStatus.OK);
	 
 }
 
 @GetMapping("shows/{movieid}/{showid}")
 public ResponseEntity<List<Shows>> getShows(@PathVariable int movieid, @PathVariable int showid){
	 
//	mqRepo.findByMovie(movieid);
	HashSet<Shows> show = new HashSet<Shows>();
	List<MovieQueue> movieQueue = mqRepo.findByMovie(movieid);
	movieQueue.forEach(queue -> {
		if(showid == queue.getShows().getScreen().getScreenid()) {
		show.add(queue.getShows());
		}
	});
	 return new ResponseEntity(show ,HttpStatus.OK);
	 
 }
	
 @GetMapping("moviequeue/{movieid}/{showid}")
 public ResponseEntity<List<Shows>> getQueue(@PathVariable int movieid, @PathVariable int showid){
	 
//	mqRepo.findByMovie(movieid);
	HashSet<MovieQueue> queues = new HashSet<MovieQueue>();
	List<MovieQueue> movieQueue = mqRepo.findByMovie(movieid);
	movieQueue.forEach(queue -> {
		if(showid == queue.getShows().getShowsid()) {
		queues.add(queue);
		}
	});
	 return new ResponseEntity(queues ,HttpStatus.OK);
	 
 }
 
 @GetMapping("rawMQ/{movieid}")
 public ResponseEntity<List<Shows>> getRawMq(@PathVariable int movieid){
	 
//	mqRepo.findByMovie(movieid);
	List<MovieQueue> movieQueue = mqRepo.findByMovie(movieid);
	 return new ResponseEntity(movieQueue ,HttpStatus.OK);
	 
 }
 

 @PostMapping("register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User u = new User();
		Login l=new Login();
	
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setMobileNo(user.getMobileNo());
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCredentialsNonExpired(true);
		u.setEnabled(true);
		u.setAuthorities(Arrays.asList("User"));
		u.setRoles("user");
		u.setEmail(user.getEmail());
		System.out.println(u);
		uRepo.save(u);
		
		l.setUsername(u.getUsername());
		l.setPassword(u.getPassword());
		loginRepo.save(l);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
 
 @GetMapping("showuser")
	public ResponseEntity <List<Login>> showAllUser(){
		
		return new ResponseEntity <List<Login>>(loginRepo.findAll(),HttpStatus.OK);
		
	}
	

}
