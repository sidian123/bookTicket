package com.team.bookTicket.pojo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;



public class Customer {
	Integer id;
	String name;
	String password;
	String sex;
	@JsonSerialize(using=ToStringSerializer.class)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	LocalDate birthday;
	String phone;
	String email;
	Integer isAdmin;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * 暂不考虑请求参数不存在的情况
	 * @return
	 */
	public boolean canUpdate() {
		if(id!=null && (!name.isEmpty() || !password.isEmpty() || !sex.isEmpty() || birthday !=null || !phone.isEmpty() || !email.isEmpty() || isAdmin !=null )) {
			return true;
		}else {
			return false;
		}
	}
	public boolean canAdd() {
		if(!name.isEmpty() && !password.isEmpty()){
			return true;
		}else {
			return false;
		}
	}
}
