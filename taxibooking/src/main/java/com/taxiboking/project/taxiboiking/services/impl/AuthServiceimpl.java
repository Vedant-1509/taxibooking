package com.taxiboking.project.taxiboiking.services.impl;

import com.taxiboking.project.taxiboiking.dto.DriverDto;
import com.taxiboking.project.taxiboiking.dto.SignupDto;
import com.taxiboking.project.taxiboiking.dto.UserDto;
import com.taxiboking.project.taxiboiking.entities.User;
import com.taxiboking.project.taxiboiking.entities.enums.Role;
import com.taxiboking.project.taxiboiking.exceptions.RuntimeConflictException;
import com.taxiboking.project.taxiboiking.repositories.UserRepository;
import com.taxiboking.project.taxiboiking.services.AuthService;
import com.taxiboking.project.taxiboiking.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceimpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user =userRepository.findByEmail(signupDto.getEmail()).orElse(null);

               // .orElseThrow(()->new RuntimeConflictException("Cannot signUp , User Already Exists with email"+signupDto.getEmail()));
        if (user !=null){
           throw  new RuntimeConflictException("Cannot signUp , User Already Exists with email"+signupDto.getEmail());
        }

        User mappedUser =modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        //create user related entities
        riderService.createNewrider(savedUser);
        //add wallet related service


        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
