package com.example.demo.model.mapper;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> userListToUserDtoList(List<User> user);

    UserDto userToUserDto(User user);
}
