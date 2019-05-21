package com.team.bookTicket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.bookTicket.dao.CustomerDao;
import com.team.bookTicket.dao.MovieDao;
import com.team.bookTicket.dao.RoomDao;
import com.team.bookTicket.dao.TicketDao;
import com.team.bookTicket.pojo.Customer;
import com.team.bookTicket.pojo.Movie;
import com.team.bookTicket.pojo.Room;
import com.team.bookTicket.pojo.Room2;
import com.team.bookTicket.pojo.Ticket;

@Service
@Transactional
public class CustomerService {
	@Autowired
	RoomDao roomDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	TicketDao ticketDao;
	
	/*------------------购票信息查阅-------------*/
	/**
	 * 获得还有影票的影片。暂时以最低效的方式实现
	 * @return
	 */
	public List<Movie> allAvailableMovie(){
		return movieDao.allAvailable();
	}
	public List<Ticket> getAllTicketsOfMovie(int movieID){
		return ticketDao.findByMovieId(movieID,null,null);
	}
	public void order(Ticket ticket) {
		ticketDao.add(ticket);
	}
	public List<Movie> allallAvailableMovie2(){
		return movieDao.allAvailable2();
	}
	public Movie getMovie(int id) {
		return movieDao.findById(id);
	}
	public Room getRoom(int id) {
		return roomDao.findById(id);
	}
	public List<Room2> getAllAvailableRoomOfMovie(int movieID) {
		return roomDao.allAvailableOfMovie(movieID);
	}
	public List<Room> soldSeatOfRoomInTime(int roomID,int movieID,LocalDateTime playTime){
		return roomDao.soldSeatOfRoomInTime(roomID,movieID, playTime);
	}
	public void updateTicket(Ticket ticket) {
		ticketDao.update2(ticket);
	}
	/*----------------个人信息查阅-----------------*/
	public Customer getCustomer(int id) {
		return customerDao.findById(id);
	}
	public void addCustomer(Customer customer) {
		customerDao.add(customer);
	}
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}
	public List<Ticket> getAllTicketsOfCustomer(int customerID) {
		return ticketDao.findByCustomerId(customerID,null,null);
	}
}















