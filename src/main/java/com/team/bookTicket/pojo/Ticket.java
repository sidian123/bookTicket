package com.team.bookTicket.pojo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class Ticket {
	Integer id;
	Integer roomID;
	Integer movieID;
	Integer customerID;
	Integer row;
	Integer column;
	Double price;
	//@JsonSerialize(using=ToStringSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime playTime;	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime purchaseTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoomID() {
		return roomID;
	}
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	public Integer getMovieID() {
		return movieID;
	}
	public void setMovieID(Integer movieID) {
		this.movieID = movieID;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getColumn() {
		return column;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDateTime getPlayTime() {
		return playTime;
	}
	public void setPlayTime(LocalDateTime playTime) {
		this.playTime = playTime;
	}
	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public boolean canAdd() {
		if(roomID!=null && movieID!=null && row!=null && column!=null && price!=null && playTime!=null) {
			return true;
		}else {
			return false;
		}
	}
	public boolean canUpdate() {
		if(id!=null && (roomID!=null || movieID!=null || customerID!=null || row!=null || column!=null || price!=null || playTime!=null || purchaseTime!=null)) {
			return true;
		}else {
			return false;
		}
	}
}
