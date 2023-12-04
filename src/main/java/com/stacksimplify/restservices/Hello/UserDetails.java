package com.stacksimplify.restservices.Hello;

import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
@ToString
public class UserDetails {
  private String firstName;
  private String lastname;
  private String place;
  
public UserDetails(String firstName, String lastname, String place) {
	super();
	this.firstName = firstName;
	this.lastname = lastname;
	this.place = place;
}
  
}
