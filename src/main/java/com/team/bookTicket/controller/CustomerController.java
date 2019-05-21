package com.team.bookTicket.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.team.bookTicket.dao.TicketDao;
import com.team.bookTicket.pojo.Movie;
import com.team.bookTicket.pojo.OrderDetail;
import com.team.bookTicket.pojo.Room;
import com.team.bookTicket.pojo.Room2;
import com.team.bookTicket.pojo.Ticket;
import com.team.bookTicket.service.CustomerService;

@RequestMapping("/customer")
@Controller
@ResponseBody
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@Autowired
	TicketDao ticketDao;
	
	@GetMapping("/get/availableMovies")
	public List<Movie> getAvailableMovies(){
		return customerService.allallAvailableMovie2();
	}
	@GetMapping("/get/movie/{id}")
	public Movie getMovie(@PathVariable Integer id) {
		return customerService.getMovie(id);
	}
	@GetMapping("/get/room/{id}")
	public Room getRoom(@PathVariable Integer id) {
		return customerService.getRoom(id);
	}
	@GetMapping("/get/room_of_movie/{movieID}")
	public List<Room2> getAvailableRoomOfMoive(@PathVariable Integer movieID){
		return customerService.getAllAvailableRoomOfMovie(movieID);
	}
	@GetMapping("/get/seats_of_room_in_time")
	public List<Room> getSeatsOfRoomInTime(@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
											@JsonSerialize(using=LocalDateTimeSerializer.class)
											@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
											@RequestParam 
											LocalDateTime playTime,
											@RequestParam Integer roomID,
											@RequestParam Integer movieID){
		if(roomID==null || playTime==null||movieID==null) {
			System.out.println("参数不存在");
			return new ArrayList<>();
		}
		return customerService.soldSeatOfRoomInTime(roomID,movieID, playTime);
	}
	@PostMapping("/update/ticket")
	public void updateTicket(Ticket ticket) {
		customerService.updateTicket(ticket);
	}
	
	/**
	 * 获得订单
	 * @param id 顾客号
	 * @return
	 */
	@GetMapping("/get/order_detail/{id}")
	public List<OrderDetail> findOrder(@PathVariable Integer id){
		return ticketDao.findOrder(id);
	}
}


























