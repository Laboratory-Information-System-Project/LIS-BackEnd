package com.douzone.lis_back.mapper.user;

import com.douzone.lis_back.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    public Optional<User> findByUsername(String username);
}
