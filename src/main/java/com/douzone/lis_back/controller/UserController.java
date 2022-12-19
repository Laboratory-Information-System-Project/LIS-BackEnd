package com.douzone.lis_back.controller;

import com.douzone.lis_back.domain.user.TokenInfo;
import com.douzone.lis_back.domain.user.UserLoginDto;
import com.douzone.lis_back.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo Login(@RequestBody UserLoginDto userLoginRequestDto){
        String username = userLoginRequestDto.getId();
        String password = userLoginRequestDto.getPw();
        System.out.println(username);
        System.out.println(password);
        TokenInfo tokenInfo = userService.login(username,password);
        return  tokenInfo;
    }
    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
