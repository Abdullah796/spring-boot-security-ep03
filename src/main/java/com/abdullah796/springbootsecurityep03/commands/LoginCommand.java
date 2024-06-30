package com.abdullah796.springbootsecurityep03.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginCommand {

    private String username;
    private String password;

}
