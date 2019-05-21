package com.team.bookTicket.pojo;

public class Room {
	Integer id;
	Integer row;
	Integer column;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public boolean canAdd() {
		if(row!=null && column!=null) {
			return true;
		}else {
			return false;
		}
	}
	public boolean canUpdate() {
		if(id!=null && (row!=null || column!=null)) {
			return true;
		}else {
			return false;
		}
	}
}
