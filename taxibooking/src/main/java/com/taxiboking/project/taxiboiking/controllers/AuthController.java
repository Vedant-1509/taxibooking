package com.taxiboking.project.taxiboiking.controllers;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.OnBoardDriverDto;
import com.taxiboking.project.taxiboiking.dto.SignupDto;
import com.taxiboking.project.taxiboiking.dto.UserDto;
import com.taxiboking.project.taxiboiking.services.AuthService;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userid,@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onboardNewDriver(userid,
                onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);

    }

}
