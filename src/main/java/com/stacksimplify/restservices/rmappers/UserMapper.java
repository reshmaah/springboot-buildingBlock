package com.stacksimplify.restservices.rmappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.stacksimplify.restservices.Entity.User;
import com.stacksimplify.restservices.dtoa.UserMsDTO;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	//user to UserDTO
	@Mappings( {
	@Mapping(target="emailaddress", source="email"),
	@Mapping(target="rolename", source="role")
	})
	UserMsDTO usermsdto(User userms);
	List<UserMsDTO> usermsdtos(List<User> users);
	//list of users tp list of users DTO
}
