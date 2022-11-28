package com.example.demo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieQueueRepository extends JpaRepository<MovieQueue,Integer>{
	
	@Query(value="select m from MovieQueue m  where m.movie.movieid =:movieid")
	public List<MovieQueue> findByMovie(@Param(value="movieid")int movieid);
	
	@Query(value="select m from MovieQueue m where m.shows.showsid =:showsid and m.date=:date")
	public MovieQueue findByShow(@Param(value="showsid")int showid,@Param(value="date")Date date);


}
