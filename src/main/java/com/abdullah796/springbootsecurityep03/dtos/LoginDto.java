package com.abdullah796.springbootsecurityep03.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginDto {

    private String jwt;
    private Boolean isLoggedIn;

}
