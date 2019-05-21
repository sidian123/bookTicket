package com.team.bookTicket.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.team.bookTicket.pojo.Ticket;


/**
 * 管理员功能类，方法中传入的参数默认正确
 * @author sidian
 *
 */
@Service
@Transactional
public class AdminService {
	@Autowired
	RoomDao roomDao;
	@Autowired
	MovieDao movieDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	TicketDao ticketDao;
	
	
	
	/*------------------房间管理--------------*/
	public void addRoom(Room room) {
		roomDao.add(room);
	}
	public void deleteRoom(int id) {
		roomDao.delete(id);
	}
	public void updateRoom(Room room) {
		roomDao.update(room);
	}
	public Room getRoom(int id) {
		return roomDao.findById(id);
	}
	public List<Room> allRoom(Integer page,Integer limit){
		if(page!=null && limit!=null) {
			return roomDao.all((page-1)*limit,limit);
		}else {
			return roomDao.all(null, null);
		}
	}
	/*------------------------影片管理-------------*/
	public void addMovie(Movie movie) {
		movieDao.add(movie);
	}
	public void deleteMovie(int id) {
		//删除影片对应的影票
		List<Ticket> tickets=ticketDao.findByMovieId(id,null,null);
		for(Ticket ticket:tickets) {
			//删除影票
			ticketDao.delete(ticket.getId());
		}
		//删除影片
		movieDao.delete(id);
	}
	public List<Movie> allMovie(Integer page,Integer limit){
		if(page!=null && limit!=null) {
			return movieDao.all((page-1)*limit,limit);
		}else {
			return movieDao.all(null, null);
		}
	}
	public Movie getMovie(Integer id) {
		return movieDao.findById(id);
	}
	public void updateMovie(Movie movie) {
		movieDao.update(movie);
	}
	/*-------------------------顾客管理-----------*/
	public void addCustomer(Customer customer) {
		customerDao.add(customer);
	}
	public void deleteCustomer(int id) {
		//删除顾客对应的影票
		List<Ticket> tickets=ticketDao.findByCustomerId(id,null,null);
		for(Ticket ticket:tickets) {
			//删除影票
			ticketDao.delete(ticket.getId());
		}
		//删除顾客
		customerDao.delete(id);
	}
	public Customer getCustomer(int id) {
		return customerDao.findById(id);
	}
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}
	/**
	 * 分页获得所有客户
	 * @param page 第几页，从1开始
	 * @param limit 每页个数
	 * @return
	 */
	public List<Customer> allCustomer(Integer page,Integer limit){
		if(page!=null && limit!=null) {
			return customerDao.all((page-1)*limit,limit);
		}else {
			return customerDao.all(null, null);
		}
	}
	/*------------------影票管理-----------------*/
	public void addTicket(Ticket ticket) {
		ticketDao.add(ticket);
	}
	public void deleteTicket(int id) {
		ticketDao.delete(id);
	}
	public List<Ticket> allTicket(Integer page,Integer limit){
		if(page!=null && limit!=null) {
			return ticketDao.all((page-1)*limit,limit);
		}else {
			return ticketDao.all(null, null);
		}
	}
	public List<Ticket> allTicket2(Integer page,Integer limit){
		if(page!=null && limit!=null) {
			return ticketDao.allGroupByRoomID((page-1)*limit,limit);
		}else {
			return ticketDao.allGroupByRoomID(null, null);
		}
	}
	/**
	 * 为房间中的所有座位生成一张影票
	 * @param ticket 含有room、movie、price和playtime；其中row和column表示行列数，不是位置
	 */
	public void addTicketForRoom(Ticket ticket) {
		for(int i=1;i<=ticket.getRow();i++) {
			for(int j=1;j<=ticket.getColumn();j++) {
				Ticket ticket2=new Ticket();
				ticket2.setRoomID(ticket.getRoomID());
				ticket2.setMovieID(ticket.getMovieID());
				ticket2.setRow(i);
				ticket2.setColumn(j);
				ticket2.setPrice(ticket.getPrice());
				ticket2.setPlayTime(ticket.getPlayTime());
				ticketDao.add(ticket2);
			}
		}
	}
	public void deleteTicketForRoom(int roomID) {
		ticketDao.deleteAllOfRoom(roomID);
	}
	
	/*--------------------------收入---------------------*/
	public Double countPriceOfDay() {
		LocalDateTime dateTime=LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
		return ticketDao.countPrice(dateTime);
	}
	public Double countPriceOfMonth() {
		LocalDateTime dateTime=LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)).withDayOfMonth(1);
		return ticketDao.countPrice(dateTime);
	}
	public Double countPriceOfQuarter() {
		LocalDateTime dateTime=LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)).withDayOfMonth(1).minusMonths(2);
		return ticketDao.countPrice(dateTime);
	}
}





















