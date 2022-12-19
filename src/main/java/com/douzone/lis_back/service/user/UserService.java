package com.douzone.lis_back.service.user;

import com.douzone.lis_back.domain.user.TokenInfo;

public interface UserService {

    public TokenInfo login(String username, String Password);
}
