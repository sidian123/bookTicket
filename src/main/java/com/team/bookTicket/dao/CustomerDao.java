package com.team.bookTicket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.bookTicket.pojo.Customer;

public interface CustomerDao {
	Customer findById(Integer id);
	List<Customer> all(@Param("offset") Integer offset,@Param("count") Integer count);
	int add(Customer customer);
	int delete(Integer id);
	int update(Customer customer);
}
