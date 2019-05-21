package com.team.bookTicket.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.bookTicket.pojo.OrderDetail;
import com.team.bookTicket.pojo.Ticket;

public interface TicketDao {
	Ticket findById(Integer id);
	
	List<Ticket> findByRoomId(@Param("roomID") Integer roomID,@Param("offset") Integer offset,@Param("count") Integer count);
	List<Ticket> findByMovieId(@Param("movieID") Integer movieID,@Param("offset") Integer offset,@Param("count") Integer count);
	List<Ticket> findByCustomerId(@Param("customerID") Integer customerID,@Param("offset") Integer offset,@Param("count") Integer count);
	List<OrderDetail> findOrder(Integer orderID);
	
	List<Ticket> all(@Param("offset") Integer offset,@Param("count") Integer count);
	List<Ticket> allAvailable(@Param("offset") Integer offset,@Param("count") Integer count);
	List<Ticket> allGroupByRoomID(@Param("offset") Integer offset,@Param("count") Integer count);
	
	int add(Ticket ticket);
	int delete(Integer id);
	int deleteAllOfRoom(Integer roomID);
	int update(Ticket ticket);
	int update2(Ticket ticket);
	
	Double countPrice(LocalDateTime dateTime);
}
