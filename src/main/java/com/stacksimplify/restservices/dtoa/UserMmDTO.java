package com.stacksimplify.restservices.dtoa;

import java.util.List;

import com.stacksimplify.restservices.Entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserMmDTO {
	private Long userid;
	private String username;
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserMmDTO(Long userid, String username, List<Order> orders) {
		super();
		this.userid = userid;
		this.username = username;
		this.orders = orders;
	}

	public UserMmDTO(Long userid, String username) {
		super();
		this.userid = userid;
		this.username = username;
	}

	public UserMmDTO() {
		super(); // TODO Auto-generated constructor stub }
	}
	
}
