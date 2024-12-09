package com.taxiboking.project.taxiboiking.services;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.SignupDto;
import com.taxiboking.project.taxiboiking.dto.UserDto;

public interface AuthService {
    String login(String email, String password);
    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId,String vechileId) ;
}
