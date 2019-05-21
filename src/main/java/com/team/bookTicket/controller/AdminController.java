package com.team.bookTicket.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.team.bookTicket.dao.MovieDao;
import com.team.bookTicket.pojo.Customer;
import com.team.bookTicket.pojo.Movie;
import com.team.bookTicket.pojo.Room;
import com.team.bookTicket.pojo.Ticket;
import com.team.bookTicket.service.AdminService;

@Controller
@RequestMapping("/admin")
@ResponseBody
public class AdminController {
	@Autowired
	AdminService adminService;
	@Value("${fileDir}")
	String fileDir;
	
	/*-----------------------客户--------------------------*/
	@GetMapping("/get/customer")
	public List<Customer> getCustomers(Integer page,Integer limit){
		return adminService.allCustomer(page,limit);
	}
	@GetMapping("/get/customer/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return adminService.getCustomer(id);
	}
	@GetMapping("/delete/customer/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		adminService.deleteCustomer(id);
	}
	@PostMapping("/update/customer")
	public void updateCustomer(Customer customer){
		if(customer.canUpdate()) {
			adminService.updateCustomer(customer);
		}
	}
	@PostMapping("/add/customer")
	public void addCustomer(Customer customer) {
		if(customer.canAdd()) {
			adminService.addCustomer(customer);
		}
	}
	
	/*----------------影片---------------------------*/
	@GetMapping("/get/movie")
	public List<Movie> getMovies(Integer page,Integer limit){
		return adminService.allMovie(page, limit);
	}
	@GetMapping("/get/movie/{id}")
	public Movie getMovie(@PathVariable Integer id) {
		return adminService.getMovie(id);
	}
	@PostMapping("/add/movie")
	public void addMovie(Movie movie) {
		if(movie.canAdd()) {
			adminService.addMovie(movie);
		}
	}
	@PostMapping("/update/movie")
	public void updateMovie(Movie movie) {
		if(movie.canUpdate()) {
			adminService.updateMovie(movie);
		}
	}
	@GetMapping("/delete/movie/{id}")
	public void deleteMovie(@PathVariable Integer id) {
		adminService.deleteMovie(id);
	}
	/*------------------房间------------------*/
	@GetMapping("/get/room")
	public List<Room> getRooms(Integer page,Integer limit){
		return adminService.allRoom(page, limit);
	}
	@GetMapping("/get/room/{id}")
	public Room getRoom(@PathVariable Integer id) {
		return adminService.getRoom(id);
	}
	@PostMapping("/add/room")
	public void addRoom(Room room) {
		if(room.canAdd()) {
			adminService.addRoom(room);
		}
	}
	@PostMapping("/update/room")
	public void updateRoom(Room room) {
		if(room.canUpdate()) {
			adminService.updateRoom(room);
		}
	}
	@GetMapping("/delete/room/{id}")
	public void deleteRoom(@PathVariable Integer id) {
		adminService.deleteRoom(id);
	}
	/*----------------------影票----------------*/
	@GetMapping("/get/ticket")
	public List<Ticket> geTickets(Integer page,Integer limit){
		return adminService.allTicket(page, limit);
	}
	@GetMapping("/get/ticket2")
	public List<Ticket> geTickets2(Integer page,Integer limit){
		return adminService.allTicket2(page, limit);
	}
	@PostMapping("/add/ticket2")
	public void addTicketForRoom(Ticket ticket) {
		if(ticket.getRoomID()!=null && ticket.getMovieID()!=null && ticket.getPrice()!=null && ticket.getPlayTime()!=null) {
			adminService.addTicketForRoom(ticket);
		}
	}
	@GetMapping("/delete/ticket2/{roomId}")
	public void deleteTicketForRoom(@PathVariable Integer roomId) {
		adminService.deleteTicketForRoom(roomId);
	}
	@GetMapping("/delete/ticket/{id}")
	public void deleteTicket(@PathVariable Integer id) {
		adminService.deleteTicket(id);
	}
	/*---------------------文件上传--------------------*/
	@PostMapping("/upload")
	public Map<String, Object> upload(@RequestParam("file") MultipartFile filePart) throws IllegalStateException, IOException {
		if(filePart!=null) {
			String filename=UUID.randomUUID()+"&"+filePart.getOriginalFilename();
			String path=fileDir+filename;
			File destFile=new File(path);
			filePart.transferTo(destFile);
			
			Map<String, Object> map=new HashMap<>();
			map.put("code", 0);
			map.put("filename",filename);
			return map;
		}else {
			Map<String, Object> map=new HashMap<>();
			map.put("code", 400);
			map.put("msg", "未知错误");
			return map;
		}
	}
	@GetMapping("/download/{name:.+}")//匹配含有中文的文件
	public ResponseEntity<byte[]> fileDownload(@PathVariable String name) throws IOException{
		String path=fileDir+name;
		File file =new File(path);
		if(!file.exists()) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", name);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		try {
			return new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()),headers,HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*---------------------收入-----------------*/
	@GetMapping("/get/price/day")
	public Double getPriceOfDay() {
		Double price=adminService.countPriceOfDay();
		if(price==null) {
			return (double) 0;
		}else {
			return price;	
		}
	}
	@GetMapping("/get/price/month")
	public Double getPriceOfMonth() {
		Double price=adminService.countPriceOfMonth();
		if(price==null) {
			return (double) 0;
		}else {
			return price;	
		}
	}
	@GetMapping("/get/price/quarter")
	public Double getPriceOfQuarter() {
		Double price=adminService.countPriceOfQuarter();
		if(price==null) {
			return (double) 0;
		}else {
			return price;	
		}
	}
}






















