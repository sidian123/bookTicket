package com.team.bookTicket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.bookTicket.pojo.Movie;

public interface MovieDao {
	Movie findById(Integer id);
	List<Movie> all(@Param("offset") Integer offset,@Param("count") Integer count);
	List<Movie> allAvailable();
	List<Movie> allAvailable2();
	int add(Movie movie);
	int delete(Integer id);
	int update(Movie movie);
}
