package com.douzone.lis_back.domain.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data

public class UserLoginDto {

    @NotNull
    private String id;
    @NotNull
    private String pw;
}
